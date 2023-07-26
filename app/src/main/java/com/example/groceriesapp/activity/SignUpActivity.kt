package com.example.groceriesapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    @SuppressLint("SuspiciousIndentation")
    private fun init() {
        binding.txtLogin.setOnClickListener {
        val mail = binding.edtSignEmail.text.toString()
        val pwd = binding.edtSignPassword.text.toString()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, pwd)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"User Created",Toast.LENGTH_SHORT).show()
                         val intent=Intent(this,LoginActivity::class.java)
                          startActivity(intent)
                    }

                }
        }
    }
}