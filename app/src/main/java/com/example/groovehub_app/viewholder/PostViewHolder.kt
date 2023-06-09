package com.example.groovehub_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.groovehub_app.databinding.PostViewBinding

class PostViewHolder(root: View): ViewHolder(root) {
    val binding = PostViewBinding.bind(root)
    val profilePhoto = binding.imageProfilePhoto
    val title = binding.txtTitle
    val username = binding.txtUsername
    val description =binding.txtDescription
    val songPhoto = binding.imageSong
}