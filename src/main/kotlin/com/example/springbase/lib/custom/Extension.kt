package com.example.springbase.lib.custom

import com.example.springbase.custom.model.pojo.Paging
import com.github.pagehelper.Page

fun <E> Page<E>.toPaging(): Paging<E> {
    val pi = this.toPageInfo()
    return Paging(
        pageNum = pi.pageNum,
        pageSize = pi.pageSize,
        startRow = pi.startRow.toInt(),
        endRow = pi.endRow.toInt(),
        total = pi.total,
        pages = pi.pages,
        firstPageNum = pi.navigateFirstPage,
        lastPageNum = pi.navigateLastPage,
        bFirstPage = pi.isIsFirstPage,
        bLastPage = pi.isIsLastPage,
        isHasPreviousPage = pi.isHasPreviousPage,
        isHasNextPage = pi.isHasNextPage,
        orderBy = this.orderBy,
        list = this.toList()
    )
}