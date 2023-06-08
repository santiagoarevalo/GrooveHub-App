package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groovehub_app.databinding.ActivitySearchSongBinding

class SearchSong : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSongBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backToPost.setOnClickListener { backToPost() }
    }

    private fun backToPost(){
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
        finish()
    }
}