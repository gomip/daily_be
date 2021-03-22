package com.example.springbase.part.com.controller

import com.example.springbase.fwk.base.BaseController
import com.example.springbase.part.com.dto.SelectDictionaryOut
import com.example.springbase.part.com.service.CdService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dictionary")
@Api(description = "딕셔너리")
class DictionaryController: BaseController() {
    @Autowired lateinit var serviceCd: CdService

    @GetMapping
    @ApiOperation(value = "딕셔너리 조회")
    fun getDictionary(): SelectDictionaryOut {
        return SelectDictionaryOut(
            codes = serviceCd.getAllCodeByTree()
        )
    }

}