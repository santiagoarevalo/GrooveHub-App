package com.example.groovehub_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.groovehub_app.adapter.NotificationAdapter
import com.example.groovehub_app.databinding.ActivityMainBinding
import com.example.groovehub_app.fragment.HomeFragment
import com.example.groovehub_app.fragment.NewsFragment
import com.example.groovehub_app.fragment.ProfileFragment
import com.example.groovehub_app.fragment.SearchFragment


class MainActivity : AppCompatActivity() {

    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var binding: ActivityMainBinding



    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationAdapter = NotificationAdapter()
        //TODO: Create notifications fragment with the recycler view
        //binding.recyclerView.adapter = adapter



        //Navigation bar

        var homeFragment = HomeFragment()
        var newsFragment = NewsFragment()
        var profileFragment = ProfileFragment()
        var searchFragment = SearchFragment()


        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> {
                    setCurrentFragment(homeFragment)
                    true
                }

                R.id.nav_news -> {
                    setCurrentFragment(newsFragment)
                    true
                }

                R.id.nav_profile -> {
                    setCurrentFragment(profileFragment)
                    true
                }

                R.id.nav_search -> {
                    setCurrentFragment(searchFragment)
                    true
                }
                else -> false

            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView,fragment)
            commit()
        }
    }

}