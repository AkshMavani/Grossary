package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.widget.Toast
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.countryCode.registerCarrierNumberEditText(binding.idEdtPhoneNumber)
        binding.btnNext.setOnClickListener {
            if (TextUtils.isEmpty(binding.idEdtPhoneNumber.text.toString())) {
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT)
                    .show()
            } else {
               // val phone = "+91" + binding.idEdtPhoneNumber.text.toString()
                val intent=Intent(this,VerificationActivity::class.java)
                intent.putExtra("phone",binding.countryCode.fullNumberWithPlus.replace(" ",""))
                startActivity(intent)
                finish()
                //    sendVerificationCode(phone)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val data = sharedPreferences.getBoolean("data", false)
        if (data) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}