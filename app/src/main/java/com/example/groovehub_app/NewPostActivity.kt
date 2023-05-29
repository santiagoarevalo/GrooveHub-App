package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groovehub_app.databinding.ActivityNewPostBinding
import com.example.groovehub_app.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPost.setOnClickListener{publishPost()}
    }

    private fun publishPost() {
        val title = binding.titlePost.text.toString()
        val description = binding.bodyPost.text.toString()
        val postOwnerUsername = getCurrentUsername()
        Log.e("USERNAME TO DB>>>>>", postOwnerUsername)
        //var usernameTest = FirebaseAuth.getInstance().currentUser!!.email.toString().substringBefore("@")
        val post = Post(0,title, postOwnerUsername, description, 0)
        val postId = UUID.randomUUID().toString()

        if(!verifyPostFields(title, description)) {
            Firebase.firestore.collection("posts").document(postId).set(post)
            Toast.makeText(this, "¡Publicación exitosa!", Toast.LENGTH_SHORT).show()
            returnMainActivity()
        }else {
            Toast.makeText(this, "Llena los campos de la publicación", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyPostFields(title: String, description: String) : Boolean {
        return (title.isEmpty() || description.isEmpty())
    }

    private fun returnMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getCurrentUsername() : String {
        var usernameLogged = ""
        if(FirebaseAuth.getInstance().currentUser != null) {
            val actualUserId = FirebaseAuth.getInstance().currentUser!!.uid
            Firebase.firestore.collection("users").document(actualUserId).get().addOnSuccessListener {
                usernameLogged = it.get("username").toString()
                Log.e("USERNAME >>>>>", usernameLogged)

            }
        }
        return usernameLogged
    }
}