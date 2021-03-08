package com.example.springbase.util

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * 21.01.05 | gomip | created
 */

@Component
class JasyptUtils(
        @Value("\${app.passwordKey}") val pwdKey : String
) {
    @Autowired lateinit var ctx: ApplicationContext

    lateinit var encryptor: PooledPBEStringEncryptor

    @PostConstruct
    fun initialize() {
        val config = SimpleStringPBEConfig()
        config.password = pwdKey
        config.algorithm = "PBEWithMD5AndDES"
        config.setPoolSize("1")
        config.providerName = "SunJCE"
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
        config.stringOutputType = "base64"

        val encryptor = PooledPBEStringEncryptor()
        encryptor.setConfig(config)
        instance = this
        this.encryptor = encryptor
    }

    companion object {
        lateinit var instance: JasyptUtils

        fun encrypt(input: String) : String{
            val encryptSettings = instance.ctx.getBean(JasyptUtils::class.java)
            return encryptSettings.encryptor.encrypt(input)
        }

        fun decrypt(input: String) : String {
            val decryptSettings = instance.ctx.getBean(JasyptUtils::class.java)
            return decryptSettings.encryptor.decrypt(input)
        }
    }
}