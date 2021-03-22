package com.example.springbase.lib.model.entity.com

import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name="com_com_cd")
data class ComComCd (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var comCdId: String = "",
    var comGrpCd: String = "",
    var comCd: String = "",
    var comCdName: String = "",
    var expl: String? = null,
    var useYn: String = "Y",
    var createUserId: String? = null,
    var createDt: OffsetDateTime? = null,
    var updateUserId: String? = null,
    var updateDt: OffsetDateTime? = null
): Serializable