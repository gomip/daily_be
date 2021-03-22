package com.example.springbase.part.com.dto

import com.example.springbase.lib.model.entity.com.ComComCd

/**
 * 21.03.22 | gomip | created
 */
data class SelectDictionaryOut (
    val codes: MutableMap<String, MutableCollection<ComComCd>>
)