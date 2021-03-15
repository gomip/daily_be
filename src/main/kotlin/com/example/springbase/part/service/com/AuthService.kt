//package com.example.springbase.part.service.com
//
//import com.auth0.jwt.JWT
//import com.auth0.jwt.JWTVerifier
//import com.auth0.jwt.algorithms.Algorithm
//import com.auth0.jwt.exceptions.JWTCreationException
//import com.example.springbase.fwk.base.BaseService
//import com.example.springbase.fwk.core.helper.Key
//import com.example.springbase.fwk.core.helper.KeyHelper
//import com.example.springbase.part.dto.com.ComUserMix
//import com.example.springbase.repo.mybatis.com.UserMapper
//import com.example.springbase.util.DateUtils
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
//import com.google.api.client.http.javanet.NetHttpTransport
//import com.google.api.client.json.gson.GsonFactory
//import com.google.api.services.oauth2.Oauth2
//import com.google.api.services.oauth2.model.Userinfo
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//import java.io.UnsupportedEncodingException
//
//
///**
// * 2021.03.12 | gomip | created
// */
//@Service
//class AuthService(
//    keyHelper: KeyHelper,
//    @Value("\${app.issuer}") val issuer: String
//): BaseService() {
//    @Autowired lateinit var userMapper: UserMapper
//
//    private final var algorithm: Algorithm
//    private final var verifier: JWTVerifier
//    final val key: String = keyHelper.get(Key.passwordLegacyKey)
//
//    init {
//        try{
//            this.algorithm = Algorithm.HMAC256(key)
//            this.verifier = JWT.require(algorithm)
//                .build()
//        } catch (e: UnsupportedEncodingException) {
//            throw Error(e)
//        }
//    }
//    fun getGoogleProfileByAccessToken(accessToken: String): Userinfo{                               // Access Token 으로 구글 프로파일 요청 & 리턴
//        try {
//            val credential = GoogleCredential().setAccessToken(accessToken)
//            val oauth2 = Oauth2.Builder(NetHttpTransport(), GsonFactory(), credential).setApplicationName(
//                "Oauth2"
//            ).build()
//            return oauth2.userinfo().get().execute()
//        } catch (e: Exception) {
//            throw e
//        }
//    }
//
//    /** 로그인 처리 - JWT 생성후 리턴
//     * 1. 사용자 정보 조회
//     * 2. JWT 생성
//     * 3. JWT가 담긴 사용자 dto 리턴
//     */
//    @Transactional(noRollbackFor = [Exception::class, RuntimeException:: class])
//    fun sign(email: String, picture: String?): ComUserMix {
//        try {
//            var comUserMix = userMapper.selectUserByEmail(email)
//
//            if (comUserMix == null) {
//                throw Exception("no user exists : $email")
//            }
//
//            comUserMix = comUserMix!!
//
//            val jwt = JWT.create()
//                .withIssuer(issuer)
//                .withClaim("email", comUserMix.email)
//                .withClaim("userId", comUserMix.userId)
//                .withClaim("userName", comUserMix.userName)
//                .withClaim("picture", picture)
//                .withIssuedAt(DateUtils.nowDate())
//                .withExpiresAt(DateUtils.fromLocalDateTimeToDate(DateUtils.now().plusDays(2)))  // 2일간 유효
//                .sign(algorithm)
//
//            comUserMix.jwt = jwt
//
//            // 라스트 로그인 시간 기록
//
//            return comUserMix
//        } catch (e: JWTCreationException) {
//            throw e
//        } catch (e: Exception) {
//            throw e
//        }
//    }
//
////    fun setCommonAreaUser(inComUserMix: ComUserMix) {
////        val user = ComUser
////    }
//}