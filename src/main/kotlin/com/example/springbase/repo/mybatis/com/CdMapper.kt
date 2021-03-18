package com.example.springbase.repo.mybatis.com

import com.example.springbase.part.com.dto.GetCdOut
import org.springframework.stereotype.Repository

@Repository
interface CdMapper {
    fun selectCdList(): List<GetCdOut>
}