package com.example.springbase.part.dto.com

import java.time.OffsetDateTime

data class ComUserMix (
    var userId: String = "",
    var email: String = "",
    var userName: String = "",
    var jwt: String = "",
    var createUserId: String? = null,
    var createDt: OffsetDateTime? = null,
    var updateUserId: String? = null,
    var updateDt: OffsetDateTime? = null
)