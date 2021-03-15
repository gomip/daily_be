//package com.example.springbase.part.controller.com
//
//import com.example.springbase.fwk.base.BaseController
//import com.example.springbase.part.service.com.AuthService
//import org.json.JSONObject
//import org.springframework.web.bind.annotation.*
//import javax.transaction.SystemException
//
///**
// * 2021.03.12 | gomip | created
// */
//@RestController
//@RequestMapping("/auth")
//class AuthController : BaseController() {
//    @Autowired lateinit var service : AuthService
//
//    @PostMapping("/sign-in/google", produces=["application/json"])
//    @ResponseBody
//    fun signIn(): String{
//        // Init ------------------------------------------------------------------------------------
//        val accessToken = request.getHeader("Authorization")
//        log.debug("accessToken : $accessToken")
//
//        // Validation ------------------------------------------------------------------------------
//        if (accessToken == null) {
//            throw SystemException("access token이 전달 되지 않았습니다.")
//        }
//
//        // Main ------------------------------------------------------------------------------------
//        try {
//            val profile = service.getGoogleProfileByAccessToken(accessToken)                        // 구글 프로파일 & 사용자 정보 조회
//
//            val userInfo = service.sign(profile.email, profile.picture)
////            service.setCommonAreaUser(userInfo)
//
//            val json = JSONObject()
//            json.put("jwt", userInfo.jwt)
//
//            return json.toString()
//        } catch (e: Exception) {
//            throw e
//        }
//    }
//}