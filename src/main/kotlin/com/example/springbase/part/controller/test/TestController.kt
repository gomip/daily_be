package com.example.springbase.part.controller.test

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.dto.test.GetTestOut
import com.example.springbase.part.dto.test.PostTestIn
import com.example.springbase.part.service.test.TestService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 21.01.05 | gomip | created
 */

@RestController
@RequestMapping("/test")
@Api("테스트 컨트롤러")
class TestController : BaseController(){
    @Autowired lateinit var service: TestService

    @GetMapping
    @ApiOperation("테스트 api")
    fun testController() : GetTestOut {
        return service.testService()
    }

    @PostMapping
    @ApiOperation("post ㅌㅔ스트")
    fun postController(@RequestBody input: PostTestIn) {
        return service.postService(input)
    }
}