package com.example.springbase.repo.mybatis.test

import com.example.springbase.part.dto.com.GetUserOut
import com.example.springbase.part.dto.test.GetTestOut
import com.example.springbase.part.dto.test.PostTestIn
import org.springframework.stereotype.Repository

/**
 * 21.01.05 | gomip | created
 */

@Repository
interface TestMapper{
    fun selectTestOne(): GetUserOut
    fun insertTestOne(input: PostTestIn)
}