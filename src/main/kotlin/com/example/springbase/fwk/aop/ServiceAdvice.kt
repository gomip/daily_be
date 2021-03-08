package com.example.springbase.fwk.aop

import ch.qos.logback.classic.Logger
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import kotlin.system.measureTimeMillis

/**
 * 21.01.03 | gomip | created
 * => 모든 Service에 대해 수행될 AOP
 */

@Component
@Aspect
class ServiceAdvice {
    companion object {
        private val log = LoggerFactory.getLogger(ServiceAdvice::class.java) as Logger
    }

    @Around("PointCutList.pointService()")
    fun aroundService(joinPoint: ProceedingJoinPoint) : Any? {
        // Init --------------------------------------------------------------------------------------------------------
        val returnVal: Any?
        val elapsed: Long
        val serviceName = "${joinPoint.signature.declaringType.simpleName}.${joinPoint.signature.name}"
        val args = joinPoint.args.toList().joinToString(",")
        val filteredArgs =
            if (args.isNotEmpty()) {
                if (args.length > 100) "with ${args.slice(0..100)}..."
                else "with $args"
            } else {
                ""
            }                                                                                                           // 서비스에서 사용된 input값
        // Main --------------------------------------------------------------------------------------------------------
        try {
            log.info(">>>>> service start [$serviceName()] $filteredArgs")
            elapsed = measureTimeMillis {
                returnVal = joinPoint.proceed()
            }
        } catch(e: Exception) {
            log.error(">>>>>  service end [$serviceName()] occurred error [${e.message}]")                         // 오류 내용 출력
            throw e
        }
        // End ---------------------------------------------------------------------------------------------------------
        log.info("<<<<<   service end [$serviceName()] [$elapsed ms]")
        return returnVal
    }
}