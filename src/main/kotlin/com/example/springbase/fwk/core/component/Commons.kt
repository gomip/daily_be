package com.example.springbase.fwk.core.component

import com.example.springbase.custom.model.pojo.ComArea
import com.example.springbase.part.com.constants.OAuthGoogleConstants
import org.apache.ibatis.session.RowBounds
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
@Scope(value = "request", proxyMode= ScopedProxyMode.TARGET_CLASS)
class Commons(
    var area : ComArea = ComArea()
) : Cloneable{
    @Autowired lateinit var oauthGoogleConstants: OAuthGoogleConstants
// transaction 저장하고 싶으면 그때 추가
    //    fun convertToTransaction(): FwkTransactionHst {
//        val tr = FwkTransactionHst()
//        tr.appName = area.appName
//        tr.appVer = area.appVer
//        tr.transactionDate = LocalDate.parse(area.date)
//        tr.gid = area.gid
//        tr.method = area.method
//        tr.path = area.path
//        tr.statCd = area.statCd
//        tr.startTime = area.startDt?.format(DateTimeFormatter.ofPattern("HHmmss"))
//        tr.endTime = area.endDt?.format(DateTimeFormatter.ofPattern("HHmmss"))
//        tr.elaps = when (area.elaps) {
//            null -> Duration.between(area.startDt, area.endDt).toMillis()
//            else -> area.elaps
//        }.toString()
//        tr.hostName = area.hostName
//        tr.remoteIp = area.remoteIp
//        tr.queryString = area.queryString
//        tr.body = area.body
//        tr.errMsg = area.errMsg
//        tr.apiId = area.apiId
//        tr.referrer = area.referrer
//        tr.createDt = DateUtils.currentTimeStamp()
//        tr.createUserId = area.user?.userId
//        tr.mobYn = when(area.mobYn) {
//            true -> "Y"
//            else -> "N"
//        }
//        return tr
//    }

    fun getRowBounds(page: Int?, limit: Int?): RowBounds {
        var rowBounds = RowBounds.DEFAULT
        if (page != null && limit != null) {
            val offset = (page - 1) * limit
            rowBounds = RowBounds(offset, limit)
        }
        return rowBounds
    }

    override fun clone(): Commons {
        return super.clone() as Commons
    }
}