package com.example.groceriesapp.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityHomeBinding
import com.example.groceriesapp.fragment.AccountFragment
import com.example.groceriesapp.fragment.CartFragment
import com.example.groceriesapp.fragment.FavouriteFragment
import com.example.groceriesapp.fragment.HomeFragment
import com.example.groceriesapp.fragment.ShopFragment
import com.example.groceriesapp.utils.DataModelClass
import com.example.groceriesapp.utils.Network
import com.google.android.material.badge.BadgeDrawable
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeActivity : AppCompatActivity() {
    var menuItem: MenuItem? = null
    private val count=1
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
     //   val imageslider=findViewById<ImageSlider>(R.id.imageslider)

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
//        val icl=findViewById<View>(R.id.include)
//        val networkConnection=Network(applicationContext)
//        networkConnection.observe(this, Observer {
//                isconnected->
//            if (isconnected){
//                icl.visibility= View.GONE
//
//            }else{
//                icl.visibility= View.VISIBLE
//
//            }
//        })
       // cart()

    }
    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }
    private fun cart(){
        var count=0
        val menuItemId: Int = binding.bottomAppBar.getMenu().getItem(2).getItemId()
        val badge: BadgeDrawable = binding.bottomAppBar.getOrCreateBadge(menuItemId)
        FirebaseDatabase.getInstance().reference.child("data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                count=0
                for (i in snapshot.children) {
                    val message = i.getValue(DataModelClass::class.java)
                    Log.e("TAG", "onDataChange:$message ",)
                    count++
                }
                    badge.number = count
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    private fun favourite(){
        var count=0

        val menuItemId: Int = binding.bottomAppBar.getMenu().getItem(3).getItemId()
        val badge: BadgeDrawable = binding.bottomAppBar.getOrCreateBadge(menuItemId)
        FirebaseDatabase.getInstance().reference.child("FavouriteData").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                count=0
                for (i in snapshot.children) {
                    val message = i.getValue(DataModelClass::class.java)
                    Log.e("TAG", "onDataChange:$message ",)
                    count++
                }
                badge.number = count
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    override fun onResume() {
        cart()
        favourite()
        super.onResume()
    }
}