package com.example.groovehub_app.model

import java.io.Serializable
data class Post(val avatar: Int = 0,
                val title: String = "",
                val username: String = "",
                val description :String = "",
                val cover : String=""
) : Serializable
