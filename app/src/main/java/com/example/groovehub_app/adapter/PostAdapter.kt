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

    init{
        posts.add(Post(0, "I really want those sax music sheets","el_mapa","I know it’s only been like a month since release, but the sax lines in this EP (particularly in this song) are too good not to try to learn. If anybody knows where I can get a hold of them, that’d be awesome, and if you haven’t already, give this project a listen, it’s very good!",0))
        posts.add(Post(0,"I will go on and on about this band","el_capy","These guys just know how to do metalcore the right way. Everything. Every minimal detail of the guitars, the care they put into the bassline coupling with the drums and the atmosphere the keys make. It just blows my mind and I can’t stop listening to both the full album and the instrumental album, all day long. If anyone is up to cover any to all of the songs of the album, hmu, I’ll do bass and/or vocals",0))
        posts.add(Post(0,"I have this song on repeat in my head","PauTrujillo","And the best part is I only know the intro guitar riff. C’mon, it might be “old” but if you ask me, it’s good. Classic good.",0))
        posts.add(Post(0,"Being Funny In A Foreign Language","the1975","Being Funny In A Foreign Language. Out Now",0))
        posts.add(Post(0,"200.000.000 \uD83C\uDDE6\uD83C\uDDF7\uD83C\uDDE8\uD83C\uDDF4","bizarrap","200.000.000 \uD83C\uDDE6\uD83C\uDDF7\uD83C\uDDE8\uD83C\uDDF4\n" +
                "gracias a todos por el apoyo \uD83E\uDEE1",0))
        posts.add(Post(0,"The Jaws Of Life","piercetheveil","The Jaws Of Life is available now. Thank you for your support, encouragement, and inspiration during the creation of our new album. None of this would even exist without you and we truly hope you enjoy it.",0))
    }

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



}