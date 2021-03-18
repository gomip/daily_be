package com.example.springbase.part.test.controller

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.com.dto.GetUserOut
import com.example.springbase.part.test.dto.PostTestIn
import com.example.springbase.part.test.service.TestService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 2021.01.05 | gomip | created
 */

@RestController
@RequestMapping("/test")
@Api("테스트 컨트롤러")
class TestController : BaseController(){
    @Autowired lateinit var service: TestService

    @GetMapping
    @ApiOperation("테스트 api")
    fun testController() : GetUserOut {
        return service.testService()
    }

    @PostMapping
    @ApiOperation("post ㅌㅔ스트")
    fun postController(@RequestBody input: PostTestIn) {
        return service.postService(input)
    }
}