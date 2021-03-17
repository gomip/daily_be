package com.example.springbase.repo.jpa.com

import com.example.springbase.lib.model.entity.com.ComUserMst
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

/**
 * 2021.03.15 | gomip | created
 */
interface ComUserMstRepo : JpaRepository<ComUserMst, String>{
    fun findByEmail(email: String) : ComUserMst
}