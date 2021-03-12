package com.example.springbase.repo.mybatis.qus

import com.example.springbase.part.dto.qus.GetQusIn
import com.example.springbase.part.dto.qus.GetQusOut
import com.github.pagehelper.Page
import org.springframework.stereotype.Repository

/**
 * 2021.03.12 | gomip | created
 */
@Repository
interface QusMapper {
    fun selectQusPaging(input: GetQusIn) : Page<GetQusOut>
}