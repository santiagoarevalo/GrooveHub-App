package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groovehub_app.api.SpotifyApiRequest
import com.example.groovehub_app.databinding.ActivityNewPostBinding
import com.example.groovehub_app.databinding.ActivitySearchSongBinding
import com.example.groovehub_app.model.Post
import com.example.groovehub_app.model.Song
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.*

class NewPostActivity : AppCompatActivity() ,PostListener{

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var searchSong: SearchSong

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchSong = SearchSong()
        binding.buttonPost.setOnClickListener { publishPost() }
        binding.backToHome.setOnClickListener { returnMainActivity() }
        binding.buttonAttachSong.setOnClickListener { attachSongToPost() }

        if(!intent.getStringExtra("songTitle").isNullOrEmpty() || !intent.getStringExtra("songCover").isNullOrEmpty()){

            val titleSong = intent.getStringExtra("songTitle")
            val artistSong = intent.getStringExtra("songArtist")
            val releaseDate = intent.getStringExtra("songDate")
            val albumName =intent.getStringExtra("songAlbum")
            val songCover = intent.getStringExtra("songCover")
            val imageView: ImageView = binding.ivPostSongCover
            binding.SongName.text= titleSong
            binding.SongArtist.text=artistSong
            binding.SongAlbum.text = albumName
            binding.SongYear.text=releaseDate
            Picasso.get().load(songCover).error(R.mipmap.ic_launcher).into(imageView)
        }
    }

    private fun publishPost() {
        val title = binding.titlePost.text.toString()
        val description = binding.bodyPost.text.toString()
        val cover:String? =binding.ivPostSongCover.tag as? String
        Log.e("Cover de cancion",cover.toString())
        getCurrentUsername { username ->
            val post = Post(0, title, username, description,cover.toString())
            val postId = UUID.randomUUID().toString()
            if (!verifyPostFields(title, description)) {
                Firebase.firestore.collection("posts").document(postId).set(post)
                Toast.makeText(this, "¡Publicación exitosa!", Toast.LENGTH_SHORT).show()
                returnMainActivity()
            } else {
                Toast.makeText(this, "Llena los campos de la publicación", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun verifyPostFields(title: String, description: String): Boolean {
        return (title.isEmpty() || description.isEmpty())
    }

    private fun returnMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getCurrentUsername(function: (String) -> Unit)  {
        var usernameLogged = ""
        if (FirebaseAuth.getInstance().currentUser != null) {
            val actualUserId = FirebaseAuth.getInstance().currentUser!!.uid
            Firebase.firestore.collection("users").document(actualUserId).get().addOnSuccessListener {
                usernameLogged = it.get("username").toString()
                function(usernameLogged)
            }
        }
    }
    private fun attachSongToPost() {
        val title = binding.titlePost.text.toString()
        val description = binding.bodyPost.text.toString()
        val intent = Intent(this, SearchSong::class.java)
        if (!verifyPostFields(title, description)) {
            intent.putExtra("postTitle", title)
            intent.putExtra("postDescription", description)
            searchSong.postListener = this // Configura el postListener en la instancia de SearchSong
        }
        startActivity(intent)
    }

    override fun onPost(title: String, description: String) {
        binding.titlePost.setText(title)
        binding.bodyPost.setText(description)
    }
}