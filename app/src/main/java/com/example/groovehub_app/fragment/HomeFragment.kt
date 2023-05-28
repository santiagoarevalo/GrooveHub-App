package com.example.groovehub_app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groovehub_app.R
import com.example.groovehub_app.adapter.PostAdapter
import com.example.groovehub_app.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home){


    private val adapter = PostAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.postsList.layoutManager= LinearLayoutManager(activity)
        binding.postsList.adapter = adapter
        binding.postsList.setHasFixedSize(true)
        return binding.root
    }
}