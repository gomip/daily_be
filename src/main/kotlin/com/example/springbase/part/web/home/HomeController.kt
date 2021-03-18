package com.example.springbase.part.web.home

import com.example.springbase.fwk.base.BaseController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/")
class HomeController : BaseController(){
    @GetMapping
    fun index(
        mv: ModelAndView
        , @RequestParam("redirect_uri") redirect_uri: String?
    ): RedirectView{
        mv.viewName = "index"

        val appName = "Daily"
        val redirectUri = "http://localhost:3001"

        return RedirectView("/web/sign-in?redirect_uri=$redirectUri&app_name=$appName")
    }
}