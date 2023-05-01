package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.example.groovehub_app.databinding.ActivityLoginBinding
import com.example.groovehub_app.databinding.ActivityRegisterBinding

class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonSignUp.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()

        }





    }

    override fun onBackPressed() {
        if(isTaskRoot){
            finish()
        }
    }


}