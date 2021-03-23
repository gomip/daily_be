package com.example.springbase.part.qus.controller

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.qus.dto.*
import com.example.springbase.part.qus.service.QusService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 2021.03.12 | gomip | QusController created
 * 2021.03.23 | gomip | 문제 등록 / 정답 등록 / 정답 조회 api 작성
 */

@RestController
@RequestMapping("/qus")
class QusController : BaseController(){
    @Autowired lateinit var service: QusService

    @GetMapping
    @ApiOperation("문제 조회")
    fun getQusPaging(input: GetQusIn): Paging<GetQusOut> {
        return service.selectQusPaging(input)
    }

    @PostMapping
    @ApiOperation("문제 등록")
    fun postQus(@RequestBody input: PostQusIn): GetQusOut {
        return service.insertQus(input)
    }

    @PostMapping("/ans")
    @ApiOperation("정답 등록")
    fun postAns(@RequestBody input: PostSolIn): GetSolOut {
        return service.insertAns(input)
    }

    @GetMapping("/{qusId}")
    @ApiOperation("정답 조회")
    fun getAns(@PathVariable qusId: String): List<GetSolOut> {
        return service.selectAns(qusId)
    }
}