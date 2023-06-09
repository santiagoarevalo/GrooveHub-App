package com.example.groovehub_app.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.groovehub_app.databinding.SongViewBinding

class SongViewHolder(root: View): ViewHolder(root) {
    val binding = SongViewBinding.bind(root)
    val title = binding.txtTitle
    val artist = binding.txtArtist
    val songImage = binding.imageSong
}