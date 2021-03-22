package com.example.springbase.repo.mybatis.com

import com.example.springbase.lib.model.entity.com.ComComGrpCd
import com.example.springbase.part.com.dto.GetCdOut
import com.example.springbase.part.com.dto.SelectCdListWithGrpCdOut
import com.github.pagehelper.Page
import org.springframework.stereotype.Repository

@Repository
interface CdMapper {
    fun selectCdList(): List<GetCdOut>

    fun selectGrpCdListForDic()     : Page<ComComGrpCd>                                             // 공통 그룹 코드 조회
    fun selectAllCdListWithGrpCd()  : MutableCollection<SelectCdListWithGrpCdOut>
}