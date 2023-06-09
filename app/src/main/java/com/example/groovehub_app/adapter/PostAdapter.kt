package com.example.groovehub_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.groovehub_app.R
import com.example.groovehub_app.model.Post
import com.example.groovehub_app.viewholder.PostViewHolder

class PostAdapter: Adapter<PostViewHolder>() {

    private var posts:ArrayList<Post> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.post_view, parent,false)
        val holder = PostViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.description.text = posts[position].description
        holder.username.text = posts[position].username
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun getPosts() : ArrayList<Post> {
        return posts
    }



}