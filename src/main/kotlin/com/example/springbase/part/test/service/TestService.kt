package com.example.springbase.part.test.service

import com.example.springbase.fwk.base.BaseService
import com.example.springbase.part.com.dto.GetUserOut
import com.example.springbase.part.test.dto.PostTestIn
import com.example.springbase.repo.mybatis.test.TestMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 2021.01.05 | gomip | created
 */
@Service
class TestService : BaseService(){
    @Autowired lateinit var mapper: TestMapper
    fun testService() : GetUserOut {
        val res = mapper.selectTestOne()
        return res
    }

    fun postService(input: PostTestIn) {
        return mapper.insertTestOne(input)
    }
}