package com.example.springbase.part.qus.dto

import java.time.OffsetDateTime

data class GetSolOut (
    var solId: String = "",
    var langCd: String = "",
    var langCdName: String = "",
    var solCtn: String = "",
    val createUserName: String? = null,
    val createDt: OffsetDateTime? = null,
    val updateUserName: String? = null,
    val updateDt: OffsetDateTime? = null
)