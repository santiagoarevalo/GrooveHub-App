package com.example.groovehub_app.model

import java.io.Serializable
data class Song(val title: String = "",
                val artist: String = "",
                val album :String = "",
                val cover : String = "",
                val releaseDate : String =""
) : Serializable
