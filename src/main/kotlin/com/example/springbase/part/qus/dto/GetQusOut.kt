package com.example.springbase.part.qus.dto

import java.time.OffsetDateTime

/**
 * 2021.03.12 | gomip | created
 */
data class GetQusOut(
    val qusId: String,
    val qusTitle: String,
    val qusCtn: String,
    val difCd: String,
    val difCdName: String,
    val tagCd: String,
    val tagCdName: String,
    val useYn: String,
    val createUserName: String? = null,
    val createDt: OffsetDateTime? = null,
    val updateUserName: String? = null,
    val updateDt: OffsetDateTime? = null
)