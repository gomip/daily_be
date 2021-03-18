package com.example.springbase.part.com.dto

data class GoogleUser(
    val sub: String,
    val email: String,
    val name: String,                                                                               // 성이름
    var familyName: String? = null,                                                                 // 성
    val givenName: String,                                                                          // 이름
    val locale: String,
    val emailVerified: Boolean,
    val picture: String                                                                             // picture url
)
