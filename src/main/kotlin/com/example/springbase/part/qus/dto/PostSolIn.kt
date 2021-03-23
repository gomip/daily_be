package com.example.springbase.part.qus.dto

import java.time.OffsetDateTime

data class PostSolIn(
    var qusId   : String,                                                                           // 문제아이디
    var langCd  : String,                                                                           // 언어 코드
    var solCtn  : String,                                                                           // 문제 내용
    var createUserId : String? = null,
    var createDt : OffsetDateTime? = null
)
