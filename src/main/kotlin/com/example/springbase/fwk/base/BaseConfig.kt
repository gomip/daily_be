package com.example.springbase.fwk.base

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.web.servlet.config.annotation.*

/**
 * 21.01.03 | gomip | created
 *
 * Cors
 * => 추가 http 헤더를 사용하여 한 출처에서 실행중인 웹 애플리케이션이 다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록
 *    브라워에 알려주는 체제
 */

@Configuration
@ComponentScan(basePackages = ["com.example.springbase"], lazyInit = true)
@EnableTransactionManagement                                                                                            // 트랜잭션 활성화
class BaseConfig : WebMvcConfigurer{
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {                                               // swagger URI 설정
        super.addResourceHandlers(registry)

        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")         // v파일 위치
            .resourceChain(false)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")                                                                            // 모든 요청 허용
            .allowedMethods("OPTIONS", "GET", "POST", "PATCH", "PUT", "DELETE")
    }

    /*

     */
    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {                                //
        configurer.defaultContentType(MediaType.APPLICATION_JSON)
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)

        registry.addViewController("/swagger-ui/")
            .setViewName("forward:/swagger-ui/index.html")
    }
}