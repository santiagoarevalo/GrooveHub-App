package com.example.groovehub_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.groovehub_app.databinding.NotificationBinding

class NotificationViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val binding = NotificationBinding.bind(root)

    //Image View
    val imgPhoto = binding.imageUserNotification

    //TextView
    val contentTV = binding.usernameTV

    //Button
    val btnFollow = binding.followBttn
}