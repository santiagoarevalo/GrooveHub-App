package com.example.groovehub_app.model

import java.io.Serializable

data class User (
    var id:String = "",
    var name:String = "",
    var lastName:String = "",
    var username:String = "",
    var email:String = ""
    ) : Serializable {
    override fun toString(): String {
        return name
        }
    }