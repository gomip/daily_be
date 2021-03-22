package com.example.springbase.fwk.aop

import ch.qos.logback.classic.Logger
import com.example.springbase.fwk.core.component.Commons
import com.example.springbase.fwk.core.component.Session
import com.example.springbase.part.com.service.AuthService
import com.example.springbase.util.DateUtils
import org.apache.commons.io.IOUtils
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.net.InetAddress
import java.net.URI
import java.net.UnknownHostException
import java.nio.charset.Charset
import java.time.OffsetDateTime
import java.time.ZoneId
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.system.measureTimeMillis

/**
 * 2021.01.03 | gomip | created
 * => 모든 Controller에 대해 수행될 AOP
 * 2021.03.22 | gomip | common area에 사용자 토큰 정보 저장
 */

@Component
@Aspect                                                                                                                 // 공통 기능을 할 클래스를 지정
class ControllerAdvice {
    companion object {
        private val log = LoggerFactory.getLogger(ControllerAdvice::class.java) as Logger
    }

    var isSetVariable = false                                                                       // 전역변수 설정 되어있는가
    lateinit var hostName: String
    lateinit var appName: String
    lateinit var profile: String

    @Autowired lateinit var ctx: ApplicationContext
    @Autowired lateinit var request: HttpServletRequest
    @Autowired lateinit var session: Session
    @Autowired lateinit var commons: Commons

    @Autowired lateinit var serviceAuth : AuthService

    @Value("\${app.version}") val appVersion: String = ""

    @Around("PointCutList.pointController()")                                                                     // 컨트롤러 전/후에 수행될 advice
    fun aroundController(joinPoint: ProceedingJoinPoint): Any? {
        // Init --------------------------------------------------------------------------------------------------------
        val controllerName = "${joinPoint.signature.declaringType.simpleName}.${joinPoint.signature.name}"
        val elapsed: Long
        val returnVal: Any?
        val req = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request

        if (!isSetVariable) {
            setStaticVariable()                                                                     // 전역변수 셋팅 : 호스트명, 어플리케이션명, 프로파일...은 지금은 없음
        }

        session.id = req.session.id                                                                 // session 저장

        setCommonArea(req, joinPoint)                                                               // Common Area 설정
        setAuth(req)                                                                                // 토큰 인증처리
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

    /**
     * common area 중 한번만 셋팅하면 되는 변수들 처리
     */
    private fun setStaticVariable() {
        hostName = try {
            InetAddress.getLocalHost().hostName
        } catch(e: UnknownHostException) {
            log.error("err occurred when get hostname: ${e.message}")
            "unknown"
        }

        val env = ctx.getBean("environment") as Environment

        profile = "local"

        appName = "Daily"

        isSetVariable = true
    }

    /**
     * common area 설정
     */
    private fun setCommonArea(req: HttpServletRequest, pjp: ProceedingJoinPoint) {
        var clientIp = req.getHeader("x-forwarded-for")
        clientIp = if (clientIp != null) {
            clientIp.split(",")[0]
        } else {
            req.remoteAddr
        }

        // common area
        commons.area.appName = appName
        commons.area.appVer = appVersion
        commons.area.date = DateUtils.currentDate()
        commons.area.gid = UUID.randomUUID().toString()
        commons.area.sessId = session.id
        commons.area.method = req.method
        commons.area.path = req.requestURI
        commons.area.startDt = OffsetDateTime.now(ZoneId.of("+9"))
        commons.area.remoteIp = clientIp
        commons.area.queryString = req.queryString
        commons.area.hostName = hostName

        if (req.getHeader("refeerer") != null) {
            val referrer = req.getHeader("referer")
            commons.area.referrer = URI(referrer).path
        }

        // body
        if (req.method in arrayOf("POST", "PATCH", "DELETE")) {
            var body = IOUtils.toString(req.inputStream, Charset.forName("UTF-8"))
            if (body.isNotEmpty()) {
                if (body.length > 4000) {
                    body = body.substring(0..4000)
                }
                body = org.apache.commons.lang3.StringUtils.chomp(body)
                body = body.replace("\n","")
                commons.area.body = body
            }
        }
    }

    /**
     * 인증처리
     */
    private fun setAuth(req: HttpServletRequest): String {
        val jwt = req.getHeader("authorization")?: return ""
        log.debug("jwt : $jwt")
        var referrer = req.getHeader("referer")

        val user = serviceAuth.decodeToken(jwt) ?: return ""
        commons.area.bLogin = true
        commons.area.user = user
        user.sessId = req.session.id

        return jwt
    }
}