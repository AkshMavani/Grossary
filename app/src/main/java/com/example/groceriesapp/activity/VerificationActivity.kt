package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityVerificationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class VerificationActivity : AppCompatActivity() {
    private var verificationId: String? = null
    private var mAuth: FirebaseAuth? = null
    lateinit var fToken:PhoneAuthProvider.ForceResendingToken
     private lateinit var binding: ActivityVerificationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        val getdata=intent.getStringExtra("phone")
        sendVerificationCode(getdata!!)
        binding.btnNext1.setOnClickListener {
            if (TextUtils.isEmpty(binding.etOtp.text.toString())) {
                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            }else if(binding.etOtp.length()<6){
                Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
            }
            else {
                verifyCode(binding.etOtp.text.toString())
            }
        }
        binding.tvResend.setOnClickListener {

        }

    }
    private fun signInWithCredential(credential: PhoneAuthCredential) {
        mAuth!!.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val i = Intent(this, LocationActivity::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG).show()
                }
            }
    }
    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBack)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private val mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationId = s
                fToken=forceResendingToken
            }
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) {
                    binding.etOtp.setText(code)
                    verifyCode(code)
                }
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@VerificationActivity, e.message, Toast.LENGTH_LONG).show()
                Log.e("TAG", "onVerificationFailed:$e" )
            }

        }


    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithCredential(credential)
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