package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth=FirebaseAuth.getInstance()
       init()
    }
    private fun init(){
        binding.txtSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val sp = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = sp.edit()
            editor.putBoolean("data", true)
            editor.apply()
            val mail=binding.edtEmail.text.toString()
            val pwd=binding.edtPassword.text.toString()
         mAuth.signInWithEmailAndPassword(mail,pwd).addOnCompleteListener { task->
                if (task.isSuccessful){
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                }else{
                    Toast.makeText(this,"Enter Valid email or password",Toast.LENGTH_SHORT).show()
                }
                    }
                        .addOnFailureListener { e->
                            Log.e("TAG", "init: $e")
                        }
            mAuth!!.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mAuth!!.currentUser?.sendEmailVerification()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Please verify email", Toast.LENGTH_LONG).show()
                        } else {
                            Log.e("Tag", "Error : ${task.exception?.message}")
                        }
                    }
                }
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