package com.example.springbase.part.com.service

import com.example.springbase.fwk.base.BaseService
import com.example.springbase.lib.model.entity.com.ComComCd
import com.example.springbase.lib.model.entity.com.ComComGrpCd
import com.example.springbase.part.com.dto.GetCdOut
import com.example.springbase.repo.mybatis.com.CdMapper
import com.example.springbase.util.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.system.measureTimeMillis

/**
 * 2021.03.17 | gomip | created
 * 2021.03.22 | gomip | dictionary용 코드 조회 생성
 */

@Service
class CdService : BaseService(){
    @Autowired lateinit var mapper: CdMapper
    private var listGrpCd: MutableCollection<ComComGrpCd> = mutableListOf()
    private var listGrpCdNm: MutableMap<String, String> = mutableMapOf()
    private var listComCd: MutableMap<String, MutableCollection<ComComCd>> = mutableMapOf()

    fun selectCdList(): List<GetCdOut> {
        return mapper.selectCdList()
    }

    private fun loadAllCodes() {
        log.info("================= Load All Code Start ====================================")
        val elapsed = measureTimeMillis {
            synchronized(this) {
                listGrpCd = mapper.selectGrpCdListForDic()                                          // 공통그룹 코드 조회

                listGrpCd.forEach {
                    listGrpCdNm[it.comGrpCd] = it.comGrpCdName                                      // 공통 그룹 코드명 세팅
                }

                val codes = mapper.selectAllCdListWithGrpCd()
                listGrpCd.stream()
                    .forEach{grpCd->
                        listComCd[grpCd.comGrpCd] = mutableListOf()
                        codes.filter{it.comGrpCd == grpCd.comGrpCd}
                            .forEach{code ->
                                val convertedCode = ObjectUtils.clone(code, ComComCd::class)
                                listComCd[grpCd.comGrpCd]!!.add(convertedCode)
                            }
                    }
            }
        }
        log.info("=================   Load All Code End ===================== : $elapsed ms ")
    }

    fun getAllCodeByTree(): MutableMap<String, MutableCollection<ComComCd>> {
        loadAllCodes()
        return listComCd
    }
}