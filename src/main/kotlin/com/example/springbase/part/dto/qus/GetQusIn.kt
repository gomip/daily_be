package com.example.springbase.part.dto.qus

/**
 * 2021.03.12 | gomip | created
 */
data class GetQusIn (
    var tagCd: String? = null,
    var difCd: String? = null,
    var pageNum : Int = 1,
    var pageSize: Int = 15,
    var orderBy: String?
)