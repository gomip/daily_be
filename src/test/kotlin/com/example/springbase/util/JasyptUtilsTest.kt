package com.example.springbase.util

import com.example.springbase.SpringBaseApplication
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * 21.01.05 | gomip | created
 */

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SpringBaseApplication::class])
class JasyptUtilsTest {
    @Test
    fun encryptTest(){
        val res = JasyptUtils.encrypt("gomip")
        val res2 = JasyptUtils.encrypt("rkskek12")
        println("encrypt : $res")
        println("encrypt : $res2")
    }

    @Test
    fun decryptTest(){
        val res = JasyptUtils.decrypt("Bg9Sj7R3o/3RG/zR1fdqe3y/bc7ElPHAgRVYMBFkyL3D9C8HIsF7AVMw0oxrSfVyf8PhgQYGH4Xvs5MySpN40zfXPTmB8lRUSsUWP38mi8qlx1ueb/P334Pm84lwUrv1")
        println("decrypt : $res")
    }
}