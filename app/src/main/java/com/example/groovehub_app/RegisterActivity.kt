package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.example.groovehub_app.databinding.ActivityRegisterBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener(::register)

        val intent = Intent(this,LoginActivity::class.java)



        val callback = object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(intent)

            }
        }

        onBackPressedDispatcher.addCallback(this,callback)

    }

    fun register(view: View){
        Firebase.auth.createUserWithEmailAndPassword(
            binding.editTextEmail?.text.toString(),
            binding.editTextPassword?.text.toString()

        )
    }


}