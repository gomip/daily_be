package com.example.springbase.part.qus.service

import com.example.springbase.custom.model.pojo.Paging
import com.example.springbase.fwk.base.BaseService
import com.example.springbase.lib.custom.toPaging
import com.example.springbase.lib.model.entity.qus.QusQusMst
import com.example.springbase.part.qus.dto.*
import com.example.springbase.repo.jpa.qus.QusQusMstRepo
import com.example.springbase.repo.mybatis.qus.QusMapper
import com.example.springbase.util.DateUtils
import com.example.springbase.util.ObjectUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 2021.03.17 | gomip | created
 * => paging 서비스 제작
 * 2021.03.23 | gomip | 문제 등록 / 정답 등록 / 정답 조회 api 작성
 */
@Service
class QusService : BaseService(){
    @Autowired lateinit var mapper: QusMapper
//    @Autowired lateinit var repo: QusQusMstRepo

    // 문제 페이징 조회
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

    // 문제 등록
    fun insertQus(input: PostQusIn) : GetQusOut{
        // Init ------------------------------------------------------------------------------------
        val userId = commons.area.user?.userId
        val createDt = DateUtils.currentTimeStamp()

        // Validation ------------------------------------------------------------------------------
        // valid 1. 제목이 비었는지 확인
        if (input.qusTitle.isEmpty()) {
            log.error("제목 입력 바랍니다")
        }
        // valid 2. 내용이 비었는지 확
        if (input.qusCtn.isEmpty()) {
            log.error("내용 입력 바랍니다.")
        }
        // valid 3. 코드들이 비었는지 확인
        if (input.tagCd.isEmpty() && input.difCd.isEmpty()) {
            log.error("코드 선택 바랍니다.")
        }
        // Main ------------------------------------------------------------------------------------
        input.createUserId = userId
        input.createDt = createDt
        val res = mapper.insertQus(input)
        return mapper.selectQusOne(res)
    }

    // 정답 등록
    fun insertAns(input: PostSolIn) : GetSolOut {
        // Init ------------------------------------------------------------------------------------
        val userId = commons.area.user?.userId
        val createDt = DateUtils.currentTimeStamp()
        // Validation ------------------------------------------------------------------------------
        // valid 1. 문제 아이디가 지정되어있는지 확인
        if (input.qusId == "") log.error("문제 아이디가 지정되지 않았습니다.")
        // valid 2. 언어 코드 확인
        if (input.langCd == "") log.error("언어가 지정되지 않았습니다.")
        // valid 3. 내용이 적혀 있는지 확인
        if (input.ansCtn == "") log.error("정답이 입력되지 않았습니다.")
        // valid 4. 해당 문제가 존재하는지 확인
        if (!mapper.isExistQus(input.qusId)) log.error("해당 문제는 존재하지 않습니다.")
        // Main ------------------------------------------------------------------------------------
        input.createUserId = userId
        input.createDt = createDt
        val res = mapper.insertAns(input)
        mapper.insertQusSolRel(res, input.qusId, userId, createDt)                                  // 문제 정답 관계 연결
        return mapper.selectAnsOne(res)
    }

    // 정답 조회
    fun selectAns(qusId: String) : List<GetSolOut> {
        // Validation ------------------------------------------------------------------------------
        // valid 1. 문제 존재 여부 확인
        if (!mapper.isExistQus(qusId)) log.error("해당 문제는 존재하지 않습니다.")

        // Main ------------------------------------------------------------------------------------
        return mapper.selectAnsAll(qusId)
    }
}