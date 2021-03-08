package com.example.springbase.fwk.config

import io.swagger.annotations.ApiOperation
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.OperationBuilderPlugin
import springfox.documentation.spi.service.contexts.OperationContext
import springfox.documentation.swagger.common.SwaggerPluginSupport

/**
 * 21.01.11 | gomip | created
 * => codegen을 한 이후에 api 함수명 변경해주는 코드
 */

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