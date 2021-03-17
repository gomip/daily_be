package com.example.springbase.part.service.qus

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseService
import com.example.springbase.lib.custom.toPaging
import com.example.springbase.part.dto.qus.GetQusIn
import com.example.springbase.part.dto.qus.GetQusOut
import com.example.springbase.repo.mybatis.qus.QusMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 2021.03.17 | gomip | created
 * => paging 서비스 제작
 */
@Service
class QusService : BaseService(){
    @Autowired lateinit var mapper: QusMapper

    fun selectQusPaging(input: GetQusIn): Paging<GetQusOut> {
        // tagCd , difCd가 각각 string으로 넘어오기 때문에 리스트 변수들을 각각 만들어준다.
        // => 왜인지 모르겠지만 query string으로 리스트를 받으면 a|b 형태를 가지게 되고, | 이녀석을 regex으로 잡을수가 없음
        var tagCdList = listOf<String>()
        if (input.tagCd != null) {
            tagCdList = input.tagCd!!.split(",")
        }
        var difCdList = listOf<String>()
        if (input.difCd != null) {
            difCdList = input.difCd!!.split(",")
        }

        startPaging(input.pageNum, input.pageSize, input.orderBy)
        return mapper.selectQusPaging(input, difCdList, tagCdList).toPaging()
    }
}