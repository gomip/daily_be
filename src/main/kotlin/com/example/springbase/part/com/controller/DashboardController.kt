package com.example.springbase.part.com.controller

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.com.service.DashboardService
import com.example.springbase.part.qus.dto.GetQusOut
import com.example.springbase.part.qus.dto.GetSolOut
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dashboard")
@Api(description = "대시보드")
class DashboardController : BaseController() {
    @Autowired lateinit var service: DashboardService

    /**
     * 최근 등록된 문제 3개 조회
     */
    @GetMapping("/qus")
    @ApiOperation("최근 등록된 문제 3개 조회")
    fun getRecentQus() : List<GetQusOut> {
        return service.selectRecentQus()
    }

    /**
     * 최근에 등록된 답안지 5개 조회
     */
    @GetMapping("/sol")
    @ApiOperation("최근에 등록된 답안 5개 조회")
    fun getRecentSol(): List<GetSolOut> {
        return service.selectRecentSol()
    }
}