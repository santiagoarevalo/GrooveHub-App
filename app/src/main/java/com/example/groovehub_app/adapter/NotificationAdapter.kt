package com.example.groovehub_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.groovehub_app.R
import com.example.groovehub_app.model.Notification
import com.example.groovehub_app.viewholder.NotificationViewHolder

class NotificationAdapter : Adapter<NotificationViewHolder>(){

    private var notifications: ArrayList<Notification> = ArrayList()

    fun setData(list: ArrayList<Notification>) {
        notifications.clear()
        notifications.addAll(list)
    }

    //This method create the view holder for recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //XML --> View
        val view = layoutInflater.inflate(R.layout.notification, parent, false)
        val holder = NotificationViewHolder(view)
        return holder
    }

    //This method binds the data to the view holder created
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.imgPhoto.setImageResource(notifications[position].id)
        holder.contentTV.text = notifications[position].username
        holder.btnFollow.text = notifications[position].body

    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}