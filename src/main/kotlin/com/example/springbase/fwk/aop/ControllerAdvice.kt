package com.example.springbase.fwk.aop

import ch.qos.logback.classic.Logger
import org.apache.commons.io.IOUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.charset.Charset
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.system.measureTimeMillis

/**
 * 2021.01.03 | gomip | created
 * => 모든 Controller에 대해 수행될 AOP
 */

@Component
@Aspect                                                                                                                 // 공통 기능을 할 클래스를 지정
class ControllerAdvice {
    companion object {
        private val log = LoggerFactory.getLogger(ControllerAdvice::class.java) as Logger
    }

    @Autowired
    lateinit var request: HttpServletRequest

    @Around("PointCutList.pointController()")                                                                     // 컨트롤러 전/후에 수행될 advice
    fun aroundController(joinPoint: ProceedingJoinPoint): Any? {
        // Init --------------------------------------------------------------------------------------------------------
        val controllerName = "${joinPoint.signature.declaringType.simpleName}.${joinPoint.signature.name}"
        val elapsed: Long
        val returnVal: Any?

        // Main --------------------------------------------------------------------------------------------------------
        try {
            log.info("  session ID : ${request.session.id}")
            log.info("   client IP : ${request.getHeader("real-ip")}")
            log.info("         GID : ${UUID.randomUUID()}")
            log.info("      method : $request.method")
            log.info(" request URL : ${request.method}")
            log.info("query string : ${request.queryString}")

            var body = IOUtils.toString(request.inputStream, Charset.forName("UTF-8"))
            body = body.replace("\n", "")
            body = if (body.isNullOrEmpty()) null
                else body
            log.info("        body : $body")
            log.info("     referer : ${request.getHeader("referer")}")
            log.info(">>>>> controller start [$controllerName()]")
            elapsed = measureTimeMillis {
                returnVal = joinPoint.proceed()
            }
        } catch(e: Exception) {
            log.info(">>>>>   controller end [$controllerName()] with error [${e.javaClass.simpleName}]")
            throw e
        }

        // End ---------------------------------------------------------------------------------------------------------
        log.info("<<<<<   controller end [$controllerName()] [$elapsed ms] ")
        return returnVal
    }
}