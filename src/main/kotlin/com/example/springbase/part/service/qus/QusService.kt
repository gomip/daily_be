package com.example.springbase.part.service.qus

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseService
import com.example.springbase.lib.custom.toPaging
import com.example.springbase.part.dto.qus.GetQusIn
import com.example.springbase.part.dto.qus.GetQusOut
import com.example.springbase.repo.mybatis.qus.QusMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QusService : BaseService(){
    @Autowired lateinit var mapper: QusMapper

    fun selectQusPaging(input: GetQusIn): Paging<GetQusOut> {
        return mapper.selectQusPaging(input).toPaging()
    }
}