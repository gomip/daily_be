package com.example.springbase.util

import ch.qos.logback.classic.Logger
import org.slf4j.LoggerFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

object DateUtils {
    val log = LoggerFactory.getLogger(DateUtils::class.java) as Logger

    val zoneId: ZoneId = ZoneId.of("Asia/Seoul")

    fun now(): LocalDateTime = LocalDateTime.ofInstant(Instant.now(), zoneId)

    fun nowDate(): Date = Date.from(now().atZone(zoneId).toInstant())

    fun fromLocalDateTimeToDate(from: LocalDateTime): Date = Date.from(from.atZone(zoneId).toInstant())

    // localDate를 Date 포맷으로 리턴
    fun fromLocalDateToDate(from: LocalDate): Date = Date.from(from.atStartOfDay(zoneId).toInstant())

    // yyyy-MM-dd 문자를 Date 포맷으로 리턴
    fun fromStrToDate(dateStr: String): Date = Date.from(LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE).atStartOfDay(zoneId).toInstant())

    // 현재 일시를 지정한 포맷으로 리턴
    fun currentDateTimeFormat(format: String): String = now().format(DateTimeFormatter.ofPattern(format))

    // 현재 일자 (yyyy-MM-dd) 리턴
    fun currentDate(): String = currentDateTimeFormat("yyyy-MM-dd")

    // 현재 시간 (HHmmss) 리턴
    fun currentTime(): String = currentDateTimeFormat("HHmmss")

    // TimeStamp(yyyy.MM.dd HH:mm:ss) 리턴
    fun currentTimeStamp(): OffsetDateTime = OffsetDateTime.now(zoneId)
    fun currentTimestampString(): String = currentDateTimeFormat("yyyy-MM-dd HH:mm:ss")

    fun parseOffsetDateTimeString(dateTime: OffsetDateTime): String = dateTime.format(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

    fun parseDateString(date: Date?): String {
        if (date == null) return ""
        return SimpleDateFormat("yyyy-MM-dd").format(date)
    }

    // 문자열 ex)19991124 -> ex)1999-11-24 형식으로 바꿔줌
    // 기안서 사용승인일 데이터 오류 관련 날짜 형식 체크를 위한 데이터형식 변경
    // 8자리, 6자리, 4자리를 제외한 나머지 데이터 공란으로 리턴
    fun formatStrToDate (str: String?) : String {
        if (str == null) {
            return ""
        }
        var str2: String
        str2 = str.replace("/-/g","")
        if (str2.length == 8) {  // IF 문자열 길이가 8이라면 0000-00-00
            return str2.substring(0, 4) + '-' + str2.substring(4, 6) + '-' + str2.substring(6)
        }
        if (str2.length == 6) { // IF 문자열 길이가 6이라면 0000-00-01
            return str2.substring(0, 4) + '-' + str2.substring(4, 6) + "-01"
        }
        if (str2.length == 4) { // IF 문자열 길이가 4라면 0000-01-01
            return str2.substring(0, 4) + "-01-01"
        }
        return ""
    }
}