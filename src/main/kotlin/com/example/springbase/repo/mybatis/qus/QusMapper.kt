package com.example.springbase.repo.mybatis.qus

import com.example.springbase.part.qus.dto.GetQusIn
import com.example.springbase.part.qus.dto.GetQusOut
import com.github.pagehelper.Page
import org.springframework.stereotype.Repository

/**
 * 2021.03.12 | gomip | created
 */
@Repository
interface QusMapper {
    fun selectQusPaging(input: GetQusIn, difCdList: List<String>, tagCdList: List<String>) : Page<GetQusOut>
}