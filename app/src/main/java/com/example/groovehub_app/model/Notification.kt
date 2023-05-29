package com.example.groovehub_app.model

data class Notification(
    val profileImage: Int,
    val username: String,
    val body: String,
    val created_at: String,
    val isFollowing: Boolean
)

