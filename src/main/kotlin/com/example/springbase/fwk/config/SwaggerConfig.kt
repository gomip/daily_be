package com.example.springbase.fwk.config

import com.google.common.base.Predicates
import io.swagger.annotations.ApiOperation
import org.apache.http.client.methods.RequestBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import springfox.documentation.builders.*
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.OperationBuilderPlugin
import springfox.documentation.spi.service.contexts.OperationContext
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger.common.SwaggerPluginSupport
import springfox.documentation.swagger2.annotations.EnableSwagger2
import springfox.documentation.service.SecurityReference
import java.util.*
import kotlin.collections.ArrayList


/**
 * 2021.01.11 | gomip | created
 * => codegen을 한 이후에 api 함수명 변경해주는 코드
 * 2021.03.22 | gomip | swagger authorization 추가
 */
@Configuration
@EnableSwagger2
class SwaggerConfig(
    @Value("\${app.version}") val version: String,
) {
    val host: String = "http://localhost:5001"

    @Bean
    @Lazy
    fun api(): Docket {
        val paramBuilder = ParameterBuilder()
        val params = ArrayList<Parameter>()

        // 모든 api 에 authorization 헤더를 추가 합니다.
        paramBuilder.name("Authorization").modelRef(ModelRef("string"))
            .parameterType("header")
            .required(true).build()
        params.add(paramBuilder.build())

        val docket = Docket(DocumentationType.SWAGGER_2)
//            .host(host)
            .apiInfo(apiInfo())
            .globalOperationParameters(params)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.springbase"))
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
            .paths(PathSelectors.regex("(?!/error).+"))
            .paths(PathSelectors.regex("(?!/system).+"))
            .build()

        return docket
    }

    fun apiInfo(): ApiInfo {
        return ApiInfoBuilder()
            .title("Daily")
            .description("Api Document")
            .version(version)
            .build()
    }
}

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1000)
class OperationNicknameIntoUniqueIdReaderV1 : OperationBuilderPlugin {
    override fun apply(context: OperationContext) {

        val methodAnnotation = context.findAnnotation(ApiOperation::class.java)
        if (methodAnnotation.isPresent) {
            val operation = methodAnnotation.get()
            if (StringUtils.hasText(operation.nickname)) {
                context.operationBuilder().uniqueId(operation.nickname)                             /* 중대사항 usingGet을 지운다. */
                context.operationBuilder().codegenMethodNameStem(operation.nickname)
            } else {
                context.operationBuilder().codegenMethodNameStem(context.name)

            }

        }
    }

    override fun supports(delimiter: DocumentationType): Boolean {
        return SwaggerPluginSupport.pluginDoesApply(delimiter)
    }
}