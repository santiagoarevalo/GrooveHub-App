package com.example.groovehub_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groovehub_app.R
import com.example.groovehub_app.adapter.NotificationAdapter
import com.example.groovehub_app.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val adapter = NotificationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentNewsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.notificationsList.layoutManager= LinearLayoutManager(activity)
        binding.notificationsList.adapter = adapter
        binding.notificationsList.setHasFixedSize(true)
        return binding.root
    }

}