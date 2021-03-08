package com.example.springbase.fwk.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest

/**
 * 21.01.03 | gomip | created
 */

abstract class BaseObject {
    companion object {
        @JvmStatic
        protected val log = LoggerFactory.getLogger("fileError") as Logger
    }

    @Autowired
    lateinit var request: HttpServletRequest
}