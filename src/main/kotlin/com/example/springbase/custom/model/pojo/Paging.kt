package com.example.springbase.custom.model.pojo

data class Paging<E>(
    var pageNum: Int = 0,
    var pageSize: Int = 0,
    var startRow: Int = 0,
    var endRow: Int = 0,
    var total: Long = 0,
    var pages: Int = 0,
    var firstPageNum: Int = 0,
    var lastPageNum: Int = 0,
    var bFirstPage: Boolean = false,
    var bLastPage: Boolean = false,
    var isHasPreviousPage: Boolean = false,
    var isHasNextPage: Boolean = false,
    var orderBy: String? = null,
    var list: List<E> = listOf()
)