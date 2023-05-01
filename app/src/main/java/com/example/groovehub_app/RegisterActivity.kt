package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.example.groovehub_app.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this,LoginActivity::class.java)



        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(intent)

            }
        }

        onBackPressedDispatcher.addCallback(this,callback)

    }


}