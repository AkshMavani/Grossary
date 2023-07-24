package com.example.groceriesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groceriesapp.ParantAdapter
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivitySeeAllBinding
import com.example.multityperecycleview.ChildModelClass
import com.example.multityperecycleview.ParentModelClass

class SeeAllActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySeeAllBinding
    private val parentModelClass:ArrayList<ParentModelClass> = ArrayList()

    private val latestlist:ArrayList<ChildModelClass> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySeeAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val position=intent.getIntExtra("position",0)
        Log.e("TAG", "onCreate: $position")

            latestlist.add(ChildModelClass(R.drawable.banana,"100","Apple","1kg","Natural Red Apple","An apple is a round fruit with red or green skin and a whitish inside. One variety of apple might be sweet, another sour. The apple isn't just a fruit.","100gr","5"))
            latestlist.add(ChildModelClass(R.drawable.mango,"20","Mango","1kg","Organic Yellow Mango  ","A mango is a sweet tropical fruit, and it's also the name of the trees on which the fruit grows","800gr","3"))
            latestlist.add(ChildModelClass(R.drawable.apple,"10","Banana","12pcs"," Natural Banana","Bananas are long, curved fruits with smooth, yellow, and sometimes slightly green skin","50gr","2"))
            latestlist.add(ChildModelClass(R.drawable.orange,"30","Orange","1kg","Organic Red orange","orange, any of several species of small trees or shrubs of the genus Citrus of the family Rutaceae and their nearly round fruits, which have leathery and oily rinds and edible, juicy inner flesh.","50gr","5"))
            latestlist.add(ChildModelClass(R.drawable.stawbary,"10","stawbary","12pcs","Organic Red Stawberry","The strawberry is a succulent and fragrant fruit of bright red colour, obtained from the plant with the same name.","100gr","5"))
            parentModelClass.add(ParentModelClass("",latestlist))

        val parantAdapter= ParantAdapter(parentModelClass, this)
        binding.rcSeeAll.layoutManager= LinearLayoutManager(this)
        binding.rcSeeAll.adapter=parantAdapter
        parantAdapter.notifyDataSetChanged()
    }
}