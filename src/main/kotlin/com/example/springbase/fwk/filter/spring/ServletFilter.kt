package com.example.springbase.fwk.filter.spring
import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.servlet.*
import javax.servlet.http.HttpServletRequest


/**
 * 서블릿 필터
 * - Http Header 를 추가/삭제 하기 위한 역할
 * - nginX 가 포워딩 해주는 헤더를 받아서, 헤더명으로 치환
 *
 * @since
 * 190522 | zany | 최초 생성
 */
@Configuration
class ServletFilter : Filter {
    companion object {
        private val log = LoggerFactory.getLogger(ServletFilter::class.java) as Logger
    }

    override fun init(filterConfig: FilterConfig?) {
        log.warn("ServletFilter] Initialize Called")
        super.init(filterConfig)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val requestWrapper = HttpRequestWrapper(request as HttpServletRequest)
        val uri = requestWrapper.requestURI

        if (log.isTraceEnabled) {
            log.trace("ServletFilter] Start by {} when {}", request.getRemoteAddr(), uri)
        }

        val reader = BufferedReader(InputStreamReader(requestWrapper.inputStream))
        val result = StringBuilder()

        var line: String?
        do {
            line = reader.readLine()
            if (line == null)
                break
            result.append(line)
        } while (true)
       
        requestWrapper.setAttribute("bodyContent", result.toString())
        requestWrapper.setAttribute("originUri", uri)

        // ==========================================================================
        // header value organize: ip
        // ==========================================================================
        if (requestWrapper.getHeader("X-Forwarded-For") != null) {
            requestWrapper.addHeader("real-ip", requestWrapper.getHeader("X-Forwarded-For")!!)
        } else{
            requestWrapper.addHeader("real-ip", request.remoteAddr)
        }

        // ==========================================================================
        // header value copy
        // - api_key 만 존재할 경우, Authorization 으로 copy
        // - Swagger 에서 api_key 로 토큰을 던짐
        // ==========================================================================
        if (requestWrapper.getHeader("api_key") != null && requestWrapper.getHeader("Authorization") == null) {
            requestWrapper.addHeader("Authorization", requestWrapper.getHeader("api_key")!!)
        }

        if (log.isTraceEnabled) {
            log.trace("ServletFilter] End")
        }

        chain.doFilter(requestWrapper, response)

    }

}