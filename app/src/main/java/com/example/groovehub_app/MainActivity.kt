package com.example.groovehub_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groovehub_app.adapter.NotificationAdapter
import com.example.groovehub_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotificationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // inflate the layout
        setContentView(binding.root) // set the content view

        adapter = NotificationAdapter()
        //TODO: Create notifications fragment with the recycler view
        //binding.recyclerView.adapter = adapter
    }
}