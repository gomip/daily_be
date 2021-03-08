package com.example.springbase.fwk.base

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Transactional

/**
 * 21.01.03 | gomip | created
 */

@Transactional
class BaseService: BaseObject() {
    protected final val log = LoggerFactory.getLogger(this::class.java) as Logger
}