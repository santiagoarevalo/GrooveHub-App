package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.groovehub_app.api.ApiController
import com.example.groovehub_app.databinding.ActivitySearchSongBinding

class SearchSong : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSongBinding
    private lateinit var SpotifyApi :ApiController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToPost.setOnClickListener { backToPost() }
        binding.searchsongButton.setOnClickListener{ searchSongOnSpotify() }
    }

    private fun backToPost(){
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun searchSongOnSpotify(){
        val songTitle = binding.inputSongtitle.text.toString()
        if (!songTitle.isEmpty()){
            //SpotifyApi.trackSearch(songTitle)
        }else{
            Toast.makeText(this, "Digite el título de la canción a buscar", Toast.LENGTH_SHORT).show()
        }

    }
}
