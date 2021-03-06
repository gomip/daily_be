package com.example.springbase.part.com.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTDecodeException
import com.auth0.jwt.interfaces.DecodedJWT
import com.example.springbase.fwk.base.BaseService
import com.example.springbase.lib.model.entity.redis.ComUser
import com.example.springbase.part.com.dto.ComUserMix
import com.example.springbase.part.com.dto.GoogleUser
import com.example.springbase.repo.mybatis.com.UserMapper
import com.example.springbase.util.DateUtils
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.http.GenericUrl
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.oauth2.Oauth2
import com.google.api.services.oauth2.model.Userinfo
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.lang.RuntimeException
import java.util.*

@Service
class AuthService(
    @Value("\${spring.security.oauth2.client.registration.google.client-id}") val clientId: String,
    @Value("\${spring.security.oauth2.client.registration.google.client-secret}") val clientSecret: String,
    @Value("\${oauth.google.userInfoEndpoint}") val userInfoEndpoint: String,
    @Value("\${oauth.google.callbackUrl}") val callbackUrl: String
) : BaseService(){

    @Autowired lateinit var mapperUser: UserMapper

    private val algorithm: Algorithm
    private val verifier: JWTVerifier
    private val scopes = Arrays.asList("email", "profile")
    private val httpTransport = NetHttpTransport()
    private val jsonFactory = GsonFactory()
//    private val jsonFactory = JacksonFactory()


    init {
        try {
            this.algorithm = Algorithm.HMAC256("?????????12")
            this.verifier = JWT.require(algorithm).build()
        } catch (e: UnsupportedEncodingException) {
            log.error("sign err : encode not supported")
            throw Error(e)
        }
    }

    /**
     * Google Auth ????????? uri ??????
     */
    fun makeGoogleOAuthRequest(callbackUrl: String, state: String): String {
        return googleAuthFlow().newAuthorizationUrl()
            .setRedirectUri(callbackUrl)
            .setState(state)
            .setAccessType("offline")
            .build()
    }

    /**
     * Google Auth Flow ??????
     */
    fun googleAuthFlow(): GoogleAuthorizationCodeFlow {
        return GoogleAuthorizationCodeFlow.Builder(
            httpTransport, jsonFactory,
            clientId,
            clientSecret,
            scopes
        ).build()
    }

    /**
     * ?????? ????????? ?????? ????????? ????????? ?????? ??????
     */
    fun googleCallback(code: String): GoogleUser {
        try{
            val tokenResponse = googleAuthFlow().newTokenRequest(code)                              // ?????? ?????? ?????? ????????? ?????? ??????
                .setRedirectUri(callbackUrl)
                .execute()

            val credential = googleAuthFlow().createAndStoreCredential(tokenResponse, null)
            val requestFactory = httpTransport.createRequestFactory(credential)
            val decoder : java.util.Base64.Decoder = java.util.Base64.getUrlDecoder()
            val parts=
                tokenResponse.idToken.split(".") // split out the "parts" (header, payload and signature)
            val payloadJson = String(decoder.decode(parts[1]))
            val json = JSONObject(payloadJson)
            val googleUser = GoogleUser(
                sub = json.getString("sub"),
                email = json.getString("email"),
                name = json.getString("name"),
                givenName = json.getString("given_name"),
                locale = json.getString("locale"),
                emailVerified = json.getBoolean("email_verified"),
                picture = json.getString("picture")
            )
            if(json.has("family_name")) {
                googleUser.familyName = json.getString("family_name")
            }

            return googleUser
        } catch (e: IOException) {
            log.error("?????? ????????? ?????? (?????? ?????? ????????? ??????) : $e")
            throw e
        }
    }

    /**
     * ?????? ????????? ??? jwt ??????
     */
    @Transactional(noRollbackFor=[Exception::class,RuntimeException::class])
    fun sign(email: String, picture: String?) : ComUserMix{
        try {
            var comUserMix = mapperUser.selectUserByEmail(email)
            if (comUserMix == null) {
                throw Exception("???????????? ???????????? ????????????.")
            }

            val jwt = JWT.create()
                .withIssuer("gomip-dev")
//                .withClaim("email", comUserMix.email)
                .withClaim("userId", comUserMix.userId)
                .withIssuedAt(DateUtils.nowDate())
                .withExpiresAt(DateUtils.fromLocalDateTimeToDate(DateUtils.now().plusDays(1)))
                .sign(algorithm)
            comUserMix.jwt = jwt

            // TODO ????????? ????????? ?????? ???????????? ????????? ????????? ??????
            return comUserMix
        } catch (e: JWTCreationException) {
            log.error("sign err: Invalid Signing configuration. Couldn't conver Claims")
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    fun setCommonAreaUser(inComUserMix: ComUserMix) {
        val user = ComUser(
            userId = inComUserMix.userId,
            email = inComUserMix.email,
            jwt = inComUserMix.jwt
        )
        commons.area.user = user
        commons.area.bLogin = true
    }

    /**
     * Access Token?????? ?????? ???????????? ??????
     */
    fun getGoogleProfileByAccessToken(accessToken: String): Userinfo{
        try {
            val credential = GoogleCredential().setAccessToken(accessToken)
            val oauth2 = Oauth2.Builder(NetHttpTransport(), GsonFactory(), credential).setApplicationName(
                "Oauth2"
            ).build()

            return oauth2.userinfo().get().execute()
        } catch (e: Exception) {
            log.error("?????? ???????????? ????????? ?????? ??????")
            throw e
        }
    }

    /**
     * ????????? ???????????? ??????
     */
    fun isValidToken(jwt: String) : Boolean{
        return try{
            if (jwt == null) return false
            verifier.verify(removeBearerSignature(jwt))
            true
        } catch (e: JWTDecodeException) {
            false
        } catch (e: Exception) {
            false
        }
    }

    /**
     * jwt ?????? BEARER ????????? ??????
     */
    private fun removeBearerSignature(jwt: String) : String{
        var token = jwt
        if (jwt.startsWith("Bearer")) {
            token = jwt.replace("Bearer".toRegex(), "").trim{it <= ' '}
        }
        return token
    }

    fun decodeToken(jwt: String) : ComUser? {
        if (!isValidToken(jwt))
            return null
        val decoded = verifier.verify(removeBearerSignature(jwt))
        val userId = decoded.getClaim("userId").asString()
//        val email = decoded.getClaim("email").asString()

        return ComUser(
            userId = userId,
//            email = email,
            jwt = jwt
        )
    }
}