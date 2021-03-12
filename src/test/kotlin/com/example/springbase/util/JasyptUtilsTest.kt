package com.example.springbase.util

import com.example.springbase.SpringBaseApplication
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * 2021.01.05 | gomip | created
 */

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringBaseApplication::class])
class JasyptUtilsTest {
    @Test
    fun encryptTest(){
        val id = "786990168451-nm8h0i3qv9baej297ceg4es3b93gk23n.apps.googleusercontent.com"
        val pwd = "e8nV7YYG8sDVfK3LTqDos0dm"

        val res = JasyptUtils.encrypt(id)
        println(res)
        val res2 = JasyptUtils.encrypt(pwd)
        println(res2)
    }

    @Test
    fun decryptTest(){
    }
}