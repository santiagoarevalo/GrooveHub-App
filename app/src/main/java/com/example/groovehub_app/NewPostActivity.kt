package com.example.groovehub_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groovehub_app.databinding.ActivityLoginBinding
import com.example.groovehub_app.databinding.ActivityNewPostBinding

class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}