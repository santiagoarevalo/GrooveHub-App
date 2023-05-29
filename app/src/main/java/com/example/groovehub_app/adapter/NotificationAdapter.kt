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

    init {
        //add 4 notifications with the same data
        notifications.add(Notification(R.drawable.ic_launcher_foreground, "santiagoav", "started following you.\n", "", true))
        notifications.add(Notification(R.drawable.ic_launcher_foreground, "paula527", "liked your post.\n", "", true))
        notifications.add(Notification(R.drawable.ic_launcher_foreground, "jhormera", "commented on your post.\n", "", false))
        notifications.add(Notification(R.drawable.ic_launcher_foreground, "camilogonz", "shared your post.\n","", false))
    }

    //This method create the view holder for recycler view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //XML --> View
        val view = layoutInflater.inflate(R.layout.notification, parent, false)
        return NotificationViewHolder(view)
    }

    //This method binds the data to the view holder created
    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.imgPhoto.setImageResource(notifications[position].profileImage)
        holder.contentTV.text = notifications[position].username + " " + notifications[position].body + " " + notifications[position].created_at
        holder.btnFollow.text = getFollowStatus(notifications[position].isFollowing)

    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    private fun getFollowStatus(isFollowing: Boolean): String {
        return if (isFollowing) {
            "Following"
        } else {
            "Follow"
        }
    }
}