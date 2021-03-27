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
    }

    @Test
    fun decryptTest(){
    }
}