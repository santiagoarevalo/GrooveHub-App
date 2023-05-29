package com.example.groovehub_app.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groovehub_app.NewPostActivity
import com.example.groovehub_app.R
import com.example.groovehub_app.adapter.PostAdapter
import com.example.groovehub_app.databinding.FragmentHomeBinding
import com.example.groovehub_app.model.Post
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment(R.layout.fragment_home){


    private val adapter = PostAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.postsList.layoutManager= LinearLayoutManager(activity)
        binding.postsList.adapter = adapter
        binding.postsList.setHasFixedSize(true)

        loadPosts()

        binding.newpostBtn.setOnClickListener {
           startActivity(Intent(binding.root.context, NewPostActivity::class.java)
               .putExtra("username", Firebase.auth.currentUser))
        }

        return binding.root
    }

    private fun loadPosts() {
        Firebase.firestore.collection("posts").get().addOnCompleteListener { task ->
            for(doc in task.result!!) {
                val post = doc.toObject(Post::class.java)
                adapter.getPosts().add(post)
                adapter.notifyDataSetChanged()
            }
        }
    }
}