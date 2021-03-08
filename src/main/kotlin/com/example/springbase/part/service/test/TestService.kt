package com.example.springbase.part.service.test

import com.example.springbase.fwk.base.BaseService
import com.example.springbase.part.dto.test.GetTestOut
import com.example.springbase.part.dto.test.PostTestIn
import com.example.springbase.repo.mybatis.test.TestMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 21.01.05 | gomip | created
 */
@Service
class TestService : BaseService(){
    @Autowired lateinit var mapper: TestMapper
    fun testService() : GetTestOut{
        return mapper.selectTestOne()
    }

    fun postService(input: PostTestIn) {
        return mapper.insertTestOne(input)
    }
}