package com.example.springbase.part.com.service

import com.example.springbase.part.qus.dto.GetQusOut
import com.example.springbase.part.qus.dto.GetSolOut
import com.example.springbase.repo.mybatis.com.DashboardMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 2021.04.04 | gomip | created
 */

@Service
class DashboardService {
    @Autowired lateinit var mapper: DashboardMapper
    // 최근에 등록된 문제 3개 조회
    fun selectRecentQus() : List<GetQusOut>{
        return mapper.selectRecentQus()
    }

    // 최근에 등록된 답안 5개 조회
    fun selectRecentSol() : List<GetSolOut> {
        return mapper.selectRecentSol()
    }
}