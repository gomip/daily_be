package com.example.springbase.lib.model.entity.redis

import org.springframework.data.annotation.Id
import java.io.Serializable


data class ComUser (
    var sessId: String = "",
    @Id var userId: String ,
    var email: String = "",
//    var roleIds: String = "",
    var jwt: String = "",
): Serializable