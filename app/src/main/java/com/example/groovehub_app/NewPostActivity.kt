package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groovehub_app.databinding.ActivityNewPostBinding
import com.example.groovehub_app.model.Post
import com.example.groovehub_app.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPost.setOnClickListener(::publishPost)
    }

    private fun publishPost(view: View) {
        val title = binding.titlePost.text.toString()
        val description = binding.bodyPost.text.toString()
        val username = FirebaseAuth.getInstance().currentUser!!.email!!.substringBefore("@")
        val post = Post(0,title, username, description, 0)
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
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getCurrentUser(callback: (User?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null) {
            val uid = currentUser.uid
            val usersRef = FirebaseDatabase.getInstance().getReference("users")

            val userListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userSnapshot = snapshot.child(uid)
                    val user = userSnapshot.getValue(User::class.java)
                    callback(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(null)
                }

            }

            usersRef.addListenerForSingleValueEvent(userListener)
        }else {
            callback(null)
        }
    }
}