package com.example.springbase.part.com.dto

import java.time.OffsetDateTime

data class GetCdOut (
    var comCdId: String = "",
    var comGrpCd: String = "",
    var comCd: String = "",
    var comCdName: String = "",
    var expl: String? = null,
    var useYn: String = "",
    val createUserName: String? = null,
    val createDt: OffsetDateTime? = null,
    val updateUserName: String? = null,
    val updateDt: OffsetDateTime? = null
)