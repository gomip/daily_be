package com.example.springbase.part.qus.dto

import java.time.OffsetDateTime

data class GetSolOut (
    var solId: String = "",
    var langCd: String = "",
    var langCdName: String = "",
    var ansCtn: String = "",
    val createUserName: String? = null,
    val createDt: OffsetDateTime? = null,
    val updateUserName: String? = null,
    val updateDt: OffsetDateTime? = null
)