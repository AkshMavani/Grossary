package com.example.groceriesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityMain2Binding
import com.example.groceriesapp.fragment.BlankFragment
import com.example.groceriesapp.utils.ParantAdapter
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.CustomAdapter
import com.example.multityperecycleview.ParentModelClass
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    var latestlist:ArrayList<ChildModelClass> = ArrayList()
    var latestlist1:ArrayList<ChildModelClass> = ArrayList()
    var latestlist2:ArrayList<ChildModelClass> = ArrayList()
    var latestlist3:ArrayList<ChildModelClass> = ArrayList()
    var latestlist4:ArrayList<ChildModelClass> = ArrayList()
    var latestlist5:ArrayList<ChildModelClass> = ArrayList()
    var latestlist6:ArrayList<ChildModelClass> = ArrayList()

    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        buildRecyclerView()
    }
    private fun loadData() {
        val sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("Fruits", null)
        val type: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist = gson.fromJson(json, type)

        val json1 = sharedPreferences.getString("Grossary", null)
        val type1: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist1 = gson.fromJson(json1, type1)

        val json2 = sharedPreferences.getString("Backery", null)
        val type2: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist2 = gson.fromJson(json2, type2)

        val json3 = sharedPreferences.getString("Vegetable", null)
        val type3: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist3 = gson.fromJson(json3, type3)

        val json4 = sharedPreferences.getString("Bevagers", null)
        val type4: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist4 = gson.fromJson(json4, type4)

        val json5 = sharedPreferences.getString("Dryfruits", null)
        val type5: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist5 = gson.fromJson(json5, type5)

        val json6 = sharedPreferences.getString("Snacks", null)
        val type6: Type = object : TypeToken<ArrayList<ChildModelClass?>?>() {}.getType()
        latestlist6 = gson.fromJson(json6, type6)
    }
    private fun buildRecyclerView() {
        parentModelClass.add(ParentModelClass("Fruits",latestlist))
        parentModelClass.add(ParentModelClass("Grossary",latestlist1))
        parentModelClass.add(ParentModelClass("Grossary",latestlist2))
        parentModelClass.add(ParentModelClass("Grossary",latestlist3))
        parentModelClass.add(ParentModelClass("Grossary",latestlist4))
        parentModelClass.add(ParentModelClass("Grossary",latestlist5))
        parentModelClass.add(ParentModelClass("Grossary",latestlist6))


        val parantAdapter= ParantAdapter(parentModelClass, this)
        binding.RC25.layoutManager= LinearLayoutManager(this)
    //    binding.RC25.layoutManager = GridLayoutManager(applicationContext,2)
        binding.RC25.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
    }
}