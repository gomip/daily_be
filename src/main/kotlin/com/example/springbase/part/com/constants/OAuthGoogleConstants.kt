package com.example.springbase.part.com.constants

import com.example.springbase.fwk.core.helper.Key
import com.example.springbase.fwk.core.helper.KeyHelper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class OAuthGoogleConstants(
    @Value("\${spring.security.oauth2.client.registration.google.client-id}") val gId: String,
    @Value("\${spring.security.oauth2.client.registration.google.client-secret") val gSecret: String,
    @Value("\${oauth.google.callbackUrl}") val callbackUrl: String,
    @Value("\${oauth.google.userInfoEndpoint}") val userInfoEndpoint: String
) {
    val clientId: String = gId
    val clientSecret: String = gSecret
}
