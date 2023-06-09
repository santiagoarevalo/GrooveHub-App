package com.example.groovehub_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groovehub_app.adapter.PostAdapter
import com.example.groovehub_app.adapter.SongAdapter
import com.example.groovehub_app.api.ApiController
import com.example.groovehub_app.databinding.ActivityNewPostBinding
import com.example.groovehub_app.databinding.ActivitySearchSongBinding
import com.example.groovehub_app.databinding.FragmentHomeBinding
import com.example.groovehub_app.model.Post
import com.example.groovehub_app.model.Song
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


interface PostListener {
    fun onPost(title: String,description:String)
}
class SearchSong : AppCompatActivity() {

    private lateinit var binding: ActivitySearchSongBinding
    private lateinit var SpotifyApi :ApiController
    private lateinit var songsToshow:ArrayList<Song>
    var postListener: PostListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        songsToshow = ArrayList()
        SpotifyApi = ApiController()
        binding.backToPost.setOnClickListener { backToPost() }
        binding.searchsongButton.setOnClickListenerSuspend {searchSongOnSpotify()}
        binding.songView.setOnItemClickListener{parent,view,position,id->
            val titleSong = songsToshow[position].title
            val artistSong = songsToshow[position].artist
            val releaseDate = songsToshow[position].releaseDate
            val albumName =songsToshow[position].album
            val songCover = songsToshow[position].cover

            val i =Intent(this,NewPostActivity::class.java)
            i.putExtra("songTitle",titleSong)
            i.putExtra("songArtist",artistSong)
            i.putExtra("songDate",releaseDate)
            i.putExtra("songAlbum",albumName)
            i.putExtra("songCover",songCover)
            savePostInfo()
            startActivity(i)
            finish()
        }
    }

    private fun savePostInfo() {
        val title = intent.getStringExtra("postTitle")
        val description = intent.getStringExtra("postDescription")
        if (postListener != null) {
            postListener!!.onPost(title.toString(), description.toString())
        }
    }
    private fun backToPost(){
        val intent = Intent(this, NewPostActivity::class.java)
        savePostInfo()
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
       songsToshow.clear()
        val songTitle = binding.inputSongtitle.text.toString()
        if (!songTitle.isEmpty()){
            val parsedSongs=  SpotifyApi.searchTrack(songTitle)
            for (s in parsedSongs){
                if (s.isNullOrEmpty()) {
                    continue
                }
                songsToshow.add(s.first())
            }
            runOnUiThread { binding.songView.adapter = SongAdapter(this,songsToshow) }
        }else{
            runOnUiThread { Toast.makeText(this, "Digite el título de la canción a buscar", Toast.LENGTH_SHORT).show()}

        }
   }

}
