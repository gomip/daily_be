package com.example.springbase.custom.model.pojo

import com.example.springbase.lib.model.entity.redis.ComUser
import com.example.springbase.util.DateUtils
import java.time.OffsetDateTime

data class ComArea(
    var transactionId: Long = 0,
    var appName: String = "",
    var appVer: String = "",
    var date: String = "",
    var gid: String = "",
    var sessId: String = "",
    var method: String = "",
    var path: String = "",
    var statCd: String? = null,
    var startDt: OffsetDateTime? = null,
    var endDt: OffsetDateTime? = null,
    var elaps: String? = "0",
    var hostName: String = "",
    var remoteIp: String = "",
    var queryString: String? = null,
    var body: String? = null,
    var errMsg: String? = null,
    var apiId: String? = null,
    var bLogin: Boolean = false,
    var user: ComUser? = null,
    var err: Exception? = null,
    val profile: String? = null,
    val startupTime: Any = ComArea.startupTime,
    var referrer: String? = null,
//    var bDev: Boolean = true,    // 로컬도 true
//    var bLocal: Boolean = true,  // 로컬만 true
//    var bPrd: Boolean = false,   // 운영계 여부
    var mobType: String = "",
    val host: String = "http://localhost:5001"
) {
    companion object {
        val startupTime = DateUtils.currentTimestampString()
    }
}


