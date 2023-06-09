package com.example.groovehub_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.groovehub_app.R
import com.example.groovehub_app.model.Post
import com.example.groovehub_app.model.Song
import com.example.groovehub_app.viewholder.SongViewHolder

class SongAdapter: Adapter<SongViewHolder>() {

    private var Songs:ArrayList<Song> = arrayListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.song_view, parent,false)
        val holder = SongViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.title.text = Songs[position].title
        holder.artist.text = Songs[position].artist
        //holder.songImage.int = Songs[position].cover
    }

    override fun getItemCount(): Int {
        return Songs.size
    }

    fun getSongs() : ArrayList<Song> {
        return Songs
    }

    fun addSongs(song: Song){
        Songs.add(song)
    }



}