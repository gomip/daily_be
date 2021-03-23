package com.example.springbase.part.qus.dto

import java.time.OffsetDateTime

data class PostQusIn(
    var qusTitle: String,                                                                           // 문제 제목
    var qusCtn  : String,                                                                           // 문제 내용
    var difCd   : String,                                                                           // 난이도 코드
    var tagCd   : String,                                                                           // 태그 코드
    var createUserId: String? = null,
    var createDt : OffsetDateTime? = null
)
