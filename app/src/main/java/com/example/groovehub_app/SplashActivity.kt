package com.example.groovehub_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //En caso de necesitar login de fondo debe realizarse en esta pantalla
        startActivity(Intent(this, MainActivity::class.java))
    }
}