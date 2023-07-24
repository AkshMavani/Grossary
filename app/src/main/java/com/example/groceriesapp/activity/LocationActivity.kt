package com.example.groceriesapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {
    val arr=ArrayList<String>()
    val arr1=ArrayList<String>()
    private lateinit var binding:ActivityLocationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        arr.add("surat")
        arr.add("Bhavnager")
        arr.add("Ahmdabad")
        arr.add("vadodra")
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arr)
        binding.spinnerZone.adapter = adapter
//        binding.spinnerZone.performClick()
        binding.spinnerZone.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.e("TAG", "onItemSelected:${arr.get(position)} ", )
                if (arr.get(position).equals("surat")){
                    arr1.clear()
                    arr1.add("Nanavarachha")
                    arr1.add("Adajan")
                    arr1.add("Dindoli")
                    arr1.add("Motavarachha")
                    arr1.add("Vesu")
                }else if (arr.get(position).equals("Bhavnager")){
                    arr1.clear()
                    arr1.add("abc")
                    arr1.add("def")
                    arr1.add("ghi")
                    arr1.add("jkl")
                    arr1.add("mno")
                }else if (arr.get(position).equals("Ahmdabad")){
                    arr1.clear()
                    arr1.add("pqr")
                    arr1.add("stu")
                    arr1.add("vwx")
                    arr1.add("yza")
                    arr1.add("aaa")
                }
                else if (arr.get(position).equals("vadodra")){
                    arr1.clear()
                    arr1.add("bbb")
                    arr1.add("ccc")
                    arr1.add("ddd")
                    arr1.add("eee")
                    arr1.add("fff")
                }
                val adapter1=ArrayAdapter(this@LocationActivity,android.R.layout.simple_spinner_dropdown_item,arr1)
                binding.spinnerArea.adapter = adapter1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }
}