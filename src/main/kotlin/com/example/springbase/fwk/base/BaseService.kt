package com.example.springbase.fwk.base

import ch.qos.logback.classic.Logger
import com.example.springbase.fwk.core.component.Commons
import com.github.pagehelper.PageHelper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.request.RequestContextHolder

/**
 * 2021.01.03 | gomip | created
 */

@Transactional
class BaseService: BaseObject() {
    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger

    @Autowired lateinit var commonReal : Commons
    var commons: Commons = Commons()
    get() = if(RequestContextHolder.getRequestAttributes() != null){
        commonReal
    } else {
        field
    }
    fun startPaging(){
        startPaging(1, 5, null)
    }

    fun startPaging(inPageNum: Int = 1, inPageSize: Int = 5, orderBy: String?) {

        if (orderBy == null)
            PageHelper.startPage<Any>(inPageNum, inPageSize)
        else
            PageHelper.startPage<Any>(inPageNum, inPageSize, orderBy)
    }
}