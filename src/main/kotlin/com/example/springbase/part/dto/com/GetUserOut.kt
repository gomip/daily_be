package com.example.springbase.part.dto.com

import java.time.OffsetDateTime

/**
 * 2021.03.11 | gomip | created
 */
class GetUserOut (
    val userId          : String,                                                                   // 사용자 아이디
    val userName        : String,                                                                   // 사용자 명
    val email           : String,                                                                   // 이메일
    val createDt        : OffsetDateTime,                                                           // 생성일시
    val createUserId    : String,                                                                   // 생성자 아이디
    val updateDt        : OffsetDateTime ?= null,                                                   // 수정일시
    val updateUserId    : String ?= null,                                                           // 수정자 아이디
)