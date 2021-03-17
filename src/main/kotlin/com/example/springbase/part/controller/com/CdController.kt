package com.example.springbase.part.controller.com

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.dto.com.GetCdOut
import com.example.springbase.part.service.com.CdService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cd")
class CdController : BaseController(){
    @Autowired lateinit var service: CdService

    @GetMapping
    @ApiOperation("코드 조회")
    fun getCdList(): List<GetCdOut>{
        return service.selectCdList()
    }

}