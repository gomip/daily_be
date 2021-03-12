package com.example.springbase.part.controller.qus

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.dto.qus.GetQusIn
import com.example.springbase.part.dto.qus.GetQusOut
import com.example.springbase.part.service.qus.QusService
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
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
    fun getQusPaging(@RequestBody input: GetQusIn): Paging<GetQusOut> {
        return service.selectQusPaging(input)
    }
}