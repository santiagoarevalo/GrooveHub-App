package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.groovehub_app.adapter.SongAdapter
import com.example.groovehub_app.api.ApiController
import com.example.groovehub_app.databinding.ActivitySearchSongBinding
import com.example.groovehub_app.model.Song
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchSong : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSongBinding
    private lateinit var SpotifyApi :ApiController
    private val adapter = SongAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        SpotifyApi = ApiController()
        binding.backToPost.setOnClickListener { backToPost() }
        binding.searchsongButton.setOnClickListenerSuspend {searchSongOnSpotify()}
    }

    private fun backToPost(){
        val intent = Intent(this, NewPostActivity::class.java)
        startActivity(intent)
        finish()
    }
    private inline fun View.setOnClickListenerSuspend(crossinline onClick: suspend () -> Unit) {
        setOnClickListener {
            GlobalScope.launch { // O utiliza el ámbito de coroutine apropiado
                onClick()
            }
        }
    }

   private suspend fun searchSongOnSpotify(){
        val songTitle = binding.inputSongtitle.text.toString()
        if (!songTitle.isEmpty()){
            val parsedSongs=  SpotifyApi.searchTrack(songTitle)
            for (s in parsedSongs){
                if (s.isNullOrEmpty()) {
                    continue
                }
                Log.e(">>>>", s.toString())
                adapter.addSongs(Song(s.get(0).title,s.get(0).artist,s.get(0).album,s.get(0).cover,s.get(0).releaseDate))
                adapter.notifyDataSetChanged()
            }
        }else{
            Toast.makeText(this, "Digite el título de la canción a buscar", Toast.LENGTH_SHORT).show()
        }
   }
}
