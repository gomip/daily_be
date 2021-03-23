package com.example.springbase.repo.jpa.qus

import com.example.springbase.lib.model.entity.qus.QusQusMst
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QusQusMstRepo : JpaRepository<QusQusMst, String> {

}