package com.example.springbase.part.com.dto

import java.util.*

data class SelectCdListWithGrpCdOut (
    var comGrpCd: String = "",
    var comGrpCdName: String,
    var cdMaxLen: Int?,
    var grpExpl: String?,
    var grpUseYn: String,
    var gprCreateUserId: String?,
    var gprCreateDt: Date? = null,
    var gprUpdateUserId: String?,
    var gprUpdateDt: Date? = null,
    var comCd: String,
    var comCdName: String,
    var expl: String?,
    var useYn: String,
    var createUserId: String?,
    var createDt: Date,
    var updateUserId: String?,
    var updateDt: Date?
)