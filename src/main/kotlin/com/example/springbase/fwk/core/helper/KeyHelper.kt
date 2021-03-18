@file:Suppress("EnumEntryName")

package com.example.springbase.fwk.core.helper

interface KeyHelper {
    fun get(keyName: Key): String
}

enum class Key {
    test,
    oauthGoogleClientId,
    oauthGoogleClientSecret,
    passwordKey,
    passwordLegacyKey,
    dbJpaManageUserName,
    dbJpaManagePassword,
    dbJpaLegacyUserName,
    dbJpaLegacyPassword,
    dbMybatisManageUserName,
    dbMybatisManagePassword,
    flywayUser,
    flywayPassword
}