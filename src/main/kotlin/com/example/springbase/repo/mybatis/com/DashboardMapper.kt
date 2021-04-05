package com.example.springbase.repo.mybatis.com

import com.example.springbase.part.qus.dto.GetQusOut
import com.example.springbase.part.qus.dto.GetSolOut
import org.springframework.stereotype.Repository

/**
 * 2021.04.04 | gomip | created
 */
@Repository
interface DashboardMapper {
    /* =================================================================================================================
       상단 레이아웃
    ================================================================================================================= */
    fun selectRecentQus() : List<GetQusOut>                                                                             // 최근 등록된 문제 3개 조회

    /* =================================================================================================================
       중단 레이아웃
    ================================================================================================================= */
    fun selectRecentSol() : List<GetSolOut>                                                                             // 최근 등록된 답안 5개 조회
}