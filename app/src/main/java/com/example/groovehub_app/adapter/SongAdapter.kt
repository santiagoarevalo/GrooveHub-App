package com.example.groovehub_app.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.groovehub_app.R
import com.example.groovehub_app.model.Song
import com.squareup.picasso.Picasso

class SongAdapter(private val context: Activity,private val songs:ArrayList<Song> ):ArrayAdapter<Song>(context,
    R.layout.song_view,songs){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.song_view,null)
        val imageView:ImageView = view.findViewById(R.id.ivSongCover)
        val title:TextView = view.findViewById(R.id.tvSongTitle)
        val artist:TextView = view.findViewById(R.id.tvSongArtist)

        Picasso.get().load(songs[position].cover).error(R.mipmap.ic_launcher).into(imageView)
        title.text= songs[position].title
        artist.text=songs[position].artist

        return view
    }

}