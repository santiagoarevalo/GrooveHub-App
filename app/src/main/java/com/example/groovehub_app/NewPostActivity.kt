package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
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

        binding.publishBttn.setOnClickListener(::publishPost)
    }

    private fun publishPost(view: View) {
        val title = binding.titleEditTxt.text.toString()
        val description = binding.descriptionEditTextMulti.text.toString()
        val username = FirebaseAuth.getInstance().currentUser!!.email.toString()
        val post = Post(0,title,username, description, 0)
        val postId = UUID.randomUUID().toString()

        if(!verifyPostFields(title, description)) {
            println(verifyPostFields(title, description))
            Firebase.firestore.collection("posts").document(postId).set(post);
            Toast.makeText(this, "¡Publicación exitosa!", Toast.LENGTH_SHORT).show();
            returnMainActivity()
        }else {
            Toast.makeText(this, "Llena los campos de la publicación", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyPostFields(title: String, description: String) : Boolean {
        return (title.isEmpty() || description.isEmpty())
    }

    private fun returnMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}