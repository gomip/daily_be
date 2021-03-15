package com.example.springbase.part.controller.com

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.service.com.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 2021.03.12 | gomip | created
 */
@RestController
@RequestMapping("/auth")
class AuthController : BaseController() {
    @Autowired lateinit var service : AuthService

    @PostMapping("/sign-in/google", produces=["application/json"])
    @ResponseBody
    fun signIn(): String{
        return "hi"
    }
}