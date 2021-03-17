package com.example.springbase.lib.model.entity.com

import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.*

/**
 * 2021.03.15 | gomip | created
 */
@Entity
@Table(name = "com_user_mst")
data class ComUserMst(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: String = "",
    var userName: String = "",
    var email: String = "",
    var createUserId: String? = null,
    var createDt : OffsetDateTime? = null,
    var updateUserId: String? = null,
    var updateDt : OffsetDateTime? = null,
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var picture: String? = null
) : Serializable
