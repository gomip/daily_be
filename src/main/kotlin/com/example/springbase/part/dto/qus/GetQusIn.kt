package com.example.springbase.part.dto.qus

/**
 * 2021.03.12 | gomip | created
 */
data class GetQusIn (
    val tagCd: String? = null,
    val pageNum : Int = 1,
    val pageSize: Int = 15,
    val orderBy: String?
)