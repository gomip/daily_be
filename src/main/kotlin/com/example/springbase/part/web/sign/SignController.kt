package com.example.springbase.part.web.sign

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.com.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/web/sign-in")
class SignController(
    @Value("\${oauth.google.callbackUrl}") val callbackUrl: String
) : BaseController(){

    @Autowired lateinit var serviceAuth: AuthService

    @GetMapping
    fun signIn(
        @RequestParam("redirect_uri") redirectUri: String?,
        @RequestParam("app_name") appName: String?
    ): RedirectView {
        val state = "$redirectUri&$appName"
        val googleUri = serviceAuth.makeGoogleOAuthRequest(callbackUrl, state)

        return RedirectView(googleUri)
    }

    @GetMapping("/fail")
    fun signInFail(
        @RequestParam("redirect_uri") redirectUri: String?,
        @RequestParam("err_msg") errMsg: String?,
        @RequestParam("err_cd") errCd: String?
    ): ModelAndView {
        val mv = ModelAndView()
        mv.viewName = "sign/sign-in-fail"
        mv.addObject("errMsg", errMsg)
        mv.addObject("errCd", errCd)
        mv.addObject("redirectUri", redirectUri)

        return mv
    }
}