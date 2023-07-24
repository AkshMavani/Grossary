package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityVerificationBinding

class VerificationActivity : AppCompatActivity() {
     private lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnNext1.setOnClickListener {
            val intent=Intent(this,LocationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}