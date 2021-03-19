package com.example.springbase.part.com.service

import com.example.springbase.fwk.base.BaseService
import com.example.springbase.part.com.dto.GetCdOut
import com.example.springbase.repo.mybatis.com.CdMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CdService : BaseService(){
    @Autowired lateinit var mapper: CdMapper

    fun selectCdList(): List<GetCdOut> {
        return mapper.selectCdList()
    }
}