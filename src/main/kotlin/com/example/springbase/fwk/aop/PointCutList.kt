package com.example.springbase.fwk.aop

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 * 21.01.03 | gomip | created
 */

@Aspect
class PointCutList {
    /* =================================================================================================================
       Controller
    ================================================================================================================= */
    @Pointcut("allController()")
    fun pointController() {

    }
    @Pointcut("execution(* com.example.springbase..controller..*.*(..))")                                        // com.example.springbase 패키지에 속한 파라미터가 0개 이상인 모든 메서드
    fun allController() {

    }
    /* =================================================================================================================
       Service
    ================================================================================================================= */
    @Pointcut("allService()")
    fun pointService() {

    }
    @Pointcut("execution(* com.example.springbase..service..*.*(..))")                                           // com.example.springbase 패키지에 속한 파라미터가 0개 이상인 모든 서비스의 메서드 호출
    fun allService() {

    }
}