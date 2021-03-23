package com.example.springbase.lib.model.entity.qus

import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "qus_qus_mst")
data class QusQusMst (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var qusId       : String = "",
    var qusTitle    : String = "",
    var difCd       : String = "",
    var qusCtn      : String = "",
    var tagCd       : String = "",
    var useYn       : String = "Y",
    var createUserId: String? = null,
    var createDt    : OffsetDateTime? = null,
    var updateUserId: String? = null,
    var updateDt    : OffsetDateTime? = null
) : Serializable