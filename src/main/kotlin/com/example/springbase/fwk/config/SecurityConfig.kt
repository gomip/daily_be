//package com.example.springbase.fwk.config
//
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import java.lang.Exception
//
//@Configuration
//class SecurityConfig : WebSecurityConfigurerAdapter() {
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .oauth2Login()
//    }
//}