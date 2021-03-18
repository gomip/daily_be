package com.example.springbase.repo.mybatis.com

import com.example.springbase.part.com.dto.ComUserMix
import org.springframework.stereotype.Repository

/**
 * 2021.03.15 | gomip | created
 */
@Repository
interface UserMapper {
    fun selectUserByEmail(email: String): ComUserMix?
}