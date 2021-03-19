package com.example.springbase.part.com.controller

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.com.service.AuthService
import io.swagger.annotations.Api
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView
import java.net.URLEncoder

@RestController
@RequestMapping("/auth")
@Api(description = "인증(로그인)")
class AuthController : BaseController(){
    @Autowired lateinit var serviceAuth: AuthService

    @PostMapping("/sign-in/google", produces = ["application/json"])
    @ResponseBody
    fun signIn(): String {
        // Init ------------------------------------------------------------------------------------
        val accessToken = request.getHeader("Authorization")
//        log.debug("access token : $accessToken")

        if (accessToken == null) {
            log.error("access-token이 전달되지 않았습니다")
        }

        try {
            val profile = serviceAuth.getGoogleProfileByAccessToken(accessToken)                    // 구글 사용자 정보 조회
            val comUserMix = serviceAuth.sign(profile.email, profile.picture)
            serviceAuth.setCommonAreaUser(comUserMix)
            val json = JSONObject()
            json.put("jwt", comUserMix.jwt)

            return json.toString()

        } catch (e: Exception) {
            log.error("로그인 처리중 에러가 발생했습니다.")
            throw e
        }
    }

    /**
     * 구글 로그인 callback
     * state는 signController.signIn()에서 조립한 redirect URL + App Name
     */
    @GetMapping("/google/callback")
    fun googleCallback(@RequestParam code: String, @RequestParam state: String): RedirectView {
        // Init ------------------------------------------------------------------------------------
        val splitString = state.split("&")
        var redirectUri = splitString[0]
        val appName = splitString[1]

        try{
            val googleUser = serviceAuth.googleCallback(code)
            val comUserMix = serviceAuth.sign(googleUser.email, googleUser.picture)
            serviceAuth.setCommonAreaUser(comUserMix)
            redirectUri += "#access_token=${comUserMix.jwt}"

            return RedirectView(redirectUri)
        } catch (e: Exception) {
            val apiHost = "http://localhost:5001"
            val errMsg = URLEncoder.encode("로그인중 에러가 발생했습니다. 개발팀에 문의해주세요", "UTF-8")
            val errCd =  URLEncoder.encode(e.message, "UTF-8")

            val failRedirectUri = "$apiHost/web/sign-in/fail?err_msg=$errMsg&err_cd=$errCd&redirect_url=${redirectUri}"
            log.info("redirectUri : $failRedirectUri")
            return RedirectView(failRedirectUri)
        }
    }

    @GetMapping("/verify")
    fun verifyToken(token: String): String{
        return if (serviceAuth.isValidToken(token)) {
            "Y"
        } else {
            "N"
        }
    }
}