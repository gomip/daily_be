package com.example.springbase.part.qus.controller

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.qus.dto.GetQusIn
import com.example.springbase.part.qus.dto.GetQusOut
import com.example.springbase.part.qus.service.QusService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 2021.03.12 | gomip | QusController created
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
}