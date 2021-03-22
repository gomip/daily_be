package com.example.springbase.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.StringUtils
import java.lang.reflect.Field
import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * 21.03.22 | gomip | created
 */
object ObjectUtils {
    private val log = LoggerFactory.getLogger(ObjectUtils::class.java) as Logger

    @Throws(IllegalAccessException::class, NoSuchFieldException::class)
    private fun getMemberFields(obj: Any): Array<Field> = obj.javaClass.declaredFields

    // O object 의 필드 중 T object 에 속한 필드의 경우 T object 의 해당 필드값에 assign 하고 T object 를 리턴.
    fun <O, T> clone(original: O, target: T, isNullInclude: Boolean = true, isEmptyInclude: Boolean = true): T where O : Any, T : Any {
        val originalFields = getMemberFields(original)
        val targetFields = getMemberFields(target)

        val originalFieldNames = originalFields.map { q -> q.name }
        var successCnt = 0

        targetFields.forEach {
            it.isAccessible = true
            if (originalFieldNames.contains(it.name)) {
                val originalField = original.javaClass.getDeclaredField(it.name)
                originalField.isAccessible = true
                try {
                    // date 데이터가 empty string 으로 들어올 경우 null 치환하여 입력

                    if (originalField.name.endsWith("Dy") && originalField.get(original) != null
                        && originalField.type.name == "java.lang.String"
                        && (it.type.name == "java.util.Date" || it.type.name == "java.sql.Date")) {
                        if (originalField.get(original) == "") it.set(target, null)
                        else it.set(target, DateUtils.parseDate(originalField.get(original).toString()))
                    }

                    else if (originalField.name.endsWith("Dt") && originalField.get(original) != null
                        && originalField.type.name == "java.lang.String"
                        && it.type.name == "java.time.OffsetDateTime") {
                        if (originalField.get(original) == "") it.set(target, null)
                        else it.set(target, DateUtils.parseDateTime(originalField.get(original).toString()))
                    }

                    // 김영민 | 20200619 | 빈 문자열 값이 들어올 경우 무시하지 않고 DB 데이터를 null 로 변경할 수 있도록 한다.
                    // 모든 Service 에 영향이 가는 내용이므로 테스트를 충분히 해본 뒤 반영할 예정
//                    else if (originalField.get(original) == "" && originalField.type.name == "java.lang.String"
//                            && it.type.name == "java.lang.String") {
//                        it.set(target, null)
//                    }

                    else if ((isNullInclude && originalField.get(original) == null)
                        || (originalField.get(original) != null && !StringUtils.isEmpty(originalField.get(original)))
                        || (isEmptyInclude && (originalField.get(original) != null && StringUtils.isEmpty(originalField.get(original))))
                    ) {
                        it.set(target, originalField.get(original))
                        successCnt += 1
                    }
                } catch (e: java.lang.IllegalArgumentException) {
                    log.trace(e.message)
                }
            }
        }

        // end
        if (log.isTraceEnabled) {
            log.trace("=============== clone object result ===============")
            log.trace("            success count : $successCnt")
            log.trace("original not mapped count : ${originalFields.count() - successCnt}")
            log.trace("target   not mapped count : ${targetFields.count() - successCnt}")
            log.trace("===================================================")
        }

        return target
    }

    /**
     * 객체 복사
     * - target 클래스 타입의 인스턴스를 생성 한 뒤
     *   original 객체의 필드명이 target 클래스의 field 명과 동일 한 경우 복사
     * @return target 클래스의 형태로 리턴
     */
    @Throws(IllegalArgumentException::class)
    fun <O, T> clone(original: O, target: KClass<T>): T where O : Any, T : Any {
        return clone(original, target.createInstance(), isNullInclude = false, isEmptyInclude = true)
    }

    /**
     * 객체 복사
     * - clone() 함수와 동일하며, 빈 문자열은 스킵.
     * @return target 클래스의 형태로 리턴
     */
    @Throws(IllegalArgumentException::class)
    fun <O, T> cloneWithoutEmptyString(original: O, target: KClass<T>): T where O : Any, T : Any {
        return clone(original, target.createInstance(), isNullInclude = false, isEmptyInclude = false)
    }

    /**
     * 객체 머지 복사
     * - original 객체를, target 객체로 복사 합니다.
     *   original 객체의 필드명이 target 객체의 필드명과 같은 경우에만 복사 합니다.
     *   필드값이 null 이거나, 빈 문자열일 경우 skip 합니다.
     * @return target 객체
     */
    @Throws(IllegalArgumentException::class)
    fun <O, T> merge(original: O, target: T): T where O : Any, T : Any {
        return clone(original, target, isNullInclude = false, isEmptyInclude = true)
    }

    /**
     * 빈 값으로 들어올 경우 null 로 변환
     * @return target 객체
     * */
    fun <T> setBlankToNull(original: T, exceptions: List<String> = listOf()): T where T : Any {
        val originalFields = getMemberFields(original)

        originalFields.forEach {
            if (exceptions.contains(it.name)) return@forEach

            val originalField = original.javaClass.getDeclaredField(it.name)
            originalField.isAccessible = true
            try {
                if (originalField.get(original) == "" && originalField.type.name == "java.lang.String") {
                    originalField.set(original, null)
                }
            } catch (e: java.lang.IllegalArgumentException) {
                log.trace(e.message)
            }
        }
        return original
    }
}