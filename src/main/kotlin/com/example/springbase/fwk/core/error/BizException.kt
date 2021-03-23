package com.example.springbase.fwk.core.error

import com.example.springbase.fwk.base.BaseException
import org.springframework.http.HttpStatus

open class BizException(
    override val httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    override val msgCd: String,
    e: Throwable? = null,
    override val msgArgs: List<String>? = null,
    override val fieldName: String? = null
) : BaseException(httpStatus, msgCd, e, msgArgs) {
    constructor(e: Throwable, msgCd: String) : this(HttpStatus.BAD_REQUEST, msgCd, e)
    constructor(e: Throwable, msgCd: String, msgArgs: List<String>?) : this(HttpStatus.BAD_REQUEST, msgCd, e, msgArgs)
    constructor(msgCd: String) : this(HttpStatus.BAD_REQUEST, msgCd)
    constructor(msgCd: String, msgArgs: List<String>?) : this(HttpStatus.BAD_REQUEST, msgCd, msgArgs = msgArgs)
    constructor(msgCd: String, msgArgs: List<String>?, fieldName:String) : this(HttpStatus.BAD_REQUEST, msgCd, msgArgs = msgArgs, fieldName = fieldName)
}
