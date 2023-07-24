package com.example.groceriesapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityHomeBinding
import com.example.groceriesapp.fragment.AccountFragment
import com.example.groceriesapp.fragment.CartFragment
import com.example.groceriesapp.fragment.FavouriteFragment
import com.example.groceriesapp.fragment.HomeFragment
import com.example.groceriesapp.fragment.ShopFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
     //   val imageslider=findViewById<ImageSlider>(R.id.imageslider)
       init()
    }
    private fun init(){
        setCurrentFragment(ShopFragment())
        binding.bottomAppBar.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.shop -> setCurrentFragment(ShopFragment())
                R.id.cart -> setCurrentFragment(CartFragment())
                R.id.explore -> setCurrentFragment(HomeFragment())
                R.id.account ->setCurrentFragment(AccountFragment())
                R.id.favorite -> setCurrentFragment(FavouriteFragment())
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }

}