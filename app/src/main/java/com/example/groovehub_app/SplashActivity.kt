package com.example.groovehub_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    private lateinit var preferences : SharedPreferences
    val pref_show_introslide = "Intro"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //En caso de necesitar login de fondo debe realizarse en esta pantallas
        preferences = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)
        if(!preferences.getBoolean(pref_show_introslide,false)){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            val editor = preferences.edit()
            editor.putBoolean(pref_show_introslide,true)
            editor.apply()
        }else{
            startActivity(Intent(this, AnotherActivity::class.java))
            finish()
        }
    }
}