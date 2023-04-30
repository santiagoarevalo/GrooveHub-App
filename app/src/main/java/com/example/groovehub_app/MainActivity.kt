package com.example.groovehub_app

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.example.groovehub_app.adapter.NotificationAdapter
import com.example.groovehub_app.databinding.ActivityMainBinding
import com.example.splashscreen.IntroSlide
import com.example.splashscreen.IntroSliderAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NotificationAdapter
    private lateinit var binding: ActivityMainBinding
    
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Welcome to the last music sharing platform you’ll ever need",
                "Welcome to Groovehub. Find your sound, find your people, share the love",
                R.drawable.sound
            ),
            IntroSlide(
                "Find your sound",
                "We bring the artists you like, and give them the power to share more than just their music",
                R.drawable.musicnote
            ),
            IntroSlide(
                "Find your people",
                "Create communities and connections around the music you love",
                R.drawable.people
            ),
            IntroSlide(
                "Share the love",
                "Got something on your mind? Share your thoughts with an interactive post",
                R.drawable.share
            )
        )
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = NotificationAdapter()
        //TODO: Create notifications fragment with the recycler view
        //binding.recyclerView.adapter = adapter
        binding.introSliderViewPager.adapter=introSliderAdapter
        binding
        setupIndicators()
        setCurrentIndicator(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.btnNext.setOnClickListener(){
            if(binding.introSliderViewPager.currentItem+1<introSliderAdapter.itemCount){
                binding.introSliderViewPager.currentItem+=1
            }else{
                Intent(applicationContext, AnotherActivity::class.java).also{
                    startActivity(it)
                    finish()
                }
            }
        }
        binding.textSkipIntro.setOnClickListener(){
            Intent(applicationContext, AnotherActivity::class.java).also{
                startActivity(it)
                finish()
            }
        }
    }

    fun setupIndicators(){
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for(i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorsContainer.addView(indicators[i])
        }

    }

    fun setCurrentIndicator(index:Int){
        val childCount= binding.indicatorsContainer.childCount
        for(i in 0 until childCount){
            val imageView = binding.indicatorsContainer[i] as ImageView
            if(i==index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}