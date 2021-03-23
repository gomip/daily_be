package com.example.springbase.repo.mybatis.qus

import com.example.springbase.part.qus.dto.*
import com.github.pagehelper.Page
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime

/**
 * 2021.03.12 | gomip | created
 * 2021.03.23 | gomip | 문제 등록 / 정답 등록 / 정답 조회 api 작성
 */
@Repository
interface QusMapper {
    /* =============================================================================================
        조회
    ============================================================================================= */
    fun selectQusPaging(input: GetQusIn, difCdList: List<String>, tagCdList: List<String>) : Page<GetQusOut>    // 문제 조회
    fun selectQusOne(qusId: String) : GetQusOut                                                     // 문제 단건 조회
    fun isExistQus(qusId: String) : Boolean                                                         // 문제 존재여부 확인
    fun selectAnsOne(solId: String) : GetSolOut                                                     // 정답 단건 조회
    fun selectAnsAll(qusId: String) : List<GetSolOut>                                               // 정답 전체 조회
    /* =============================================================================================
        등록
    ============================================================================================= */
    fun insertQus(input: PostQusIn): String                                                         // 문제 등록
    fun insertAns(input: PostSolIn): String                                                         // 정답 등록
    fun insertQusSolRel(solId: String, qusId: String, userId: String?, createDt: OffsetDateTime?)}  // 문제 정답 관계 테이블 등록