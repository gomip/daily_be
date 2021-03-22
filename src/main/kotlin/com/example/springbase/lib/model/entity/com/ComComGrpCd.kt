package com.example.springbase.lib.model.entity.com

import com.fasterxml.jackson.annotation.JsonInclude
import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name="com_com_grp_cd")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ComComGrpCd(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var comGrpCdId: String = "",
    var comGrpCd: String = "",
    var comGrpCdName: String = "",
    var cdMaxLen: Int = 0,
    var expl: String? = null,
    var useYn: String = "Y",
    var createUserId: String? = null,
    var createDt: OffsetDateTime? = null,
    var updateUserId: String? = null,
    var updateDt: OffsetDateTime? = null
): Serializable
