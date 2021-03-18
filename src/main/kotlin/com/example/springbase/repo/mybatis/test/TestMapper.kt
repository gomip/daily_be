package com.example.springbase.repo.mybatis.test

import com.example.springbase.part.com.dto.GetUserOut
import com.example.springbase.part.test.dto.PostTestIn
import org.springframework.stereotype.Repository

/**
 * 2021.01.05 | gomip | created
 */

@Repository
interface TestMapper{
    fun selectTestOne(): GetUserOut
    fun insertTestOne(input: PostTestIn)
}