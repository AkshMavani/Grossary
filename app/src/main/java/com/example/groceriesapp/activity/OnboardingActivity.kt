package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.demo_walkthrough.OnboardingViewPagerAdapter
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityOnboardingBinding
import com.google.android.material.tabs.TabLayoutMediator

class OnboardingActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var textSkip: TextView
    private lateinit var binding:ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mViewPager = binding.viewPager
        mViewPager.adapter = OnboardingViewPagerAdapter(this, this)
        TabLayoutMediator(binding.pageIndicator, mViewPager) { _, _ -> }.attach()
        textSkip = findViewById(R.id.text_skip)
        textSkip.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, SplashScreen::class.java)
            startActivity(intent)
        }

        val btnNextStep: Button = findViewById(R.id.btn_next_step)

        btnNextStep.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                finish()
                val intent =
                    Intent(applicationContext, SplashScreen::class.java)
                startActivity(intent)
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }

    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }
}