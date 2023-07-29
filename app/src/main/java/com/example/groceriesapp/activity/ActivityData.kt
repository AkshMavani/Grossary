package com.example.groceriesapp.activity

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.example.groceriesapp.R
import com.example.groceriesapp.databinding.ActivityDataBinding
import com.example.groceriesapp.utils.DataModelClass
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ActivityData : AppCompatActivity() {
    var fill=false
    private var score=0
    private lateinit var binding:ActivityDataBinding
    private var storage: FirebaseStorage?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseStorage.getInstance()
        val dt=intent.getIntExtra("images",0)
        binding.imageView5.setImageResource(dt)
        val lname=intent.getStringExtra("lname")
        val nuration=intent.getStringExtra("nuration")
        val rating=intent.getStringExtra("rating")
        val pdetail=intent.getStringExtra("pdetail")
        val name=intent.getStringExtra("name")
        val price=intent.getStringExtra("price")
        val pc=intent.getStringExtra("pc")


        binding.TvDetail.text=lname
        binding.tvPrice.text=price
        binding.tvPdetail.text=pdetail
        binding.tvNuration.text=nuration
        binding.ratingBar.rating= rating!!.toFloat()
        var pricedata=0
        binding.imgplus.setOnClickListener {
            score++
            Log.e("TAG", "onCreate:${score * price!!.toInt()}")
            pricedata=score * price!!.toInt()
            Log.e("TAG", "onCreate:$pricedata")
            binding.txtnum.text= score.toString()
            binding.tvPrice.text=pricedata.toString()



        }
        binding.imgminus.setOnClickListener {
            if (score>0) {
                score--
                binding.txtnum.text = score.toString()
                pricedata -= price!!.toInt()
                Log.e("TAG", "onCreate:$pricedata")
                binding.tvPrice.text=pricedata.toString()

            }
        }
         binding.btnAddBasket.setOnClickListener {
             Log.e("TAG", "Pricedata:$pricedata " )
             val storageRef = storage!!.reference
             val mountainsRef = storageRef.child(dt.toString())
             binding.imageView5.isDrawingCacheEnabled = true
            binding.imageView5.buildDrawingCache()
             val bitmap = (binding.imageView5.drawable as BitmapDrawable).bitmap
             val baos = ByteArrayOutputStream()
             bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
             val data = baos.toByteArray()

             var uploadTask = mountainsRef.putBytes(data)
             uploadTask.addOnFailureListener {
                 Log.e("TAG", "onCreate:fail ")
                 // Handle unsuccessful uploads
             }.addOnCompleteListener { taskSnapshot ->
                 Log.e("TAG", "onCreate:success " )
                    if (taskSnapshot.isSuccessful){
                    mountainsRef.downloadUrl.addOnSuccessListener { task->
                        Log.e("TAG", "onCreate:$task " )

                        val productDetail=DataModelClass(img =task.toString(),lname=lname.toString(),rating=rating.toString(), description = pdetail.toString(),price=price.toString(), review =nuration.toString(), ftitle =name.toString(), kg = pc.toString()  )
                        FirebaseDatabase.getInstance().reference.child("data").child(name.toString()).setValue(productDetail).addOnCompleteListener {
                            task->
                            if (task.isSuccessful){
                                Toast.makeText(this,"Data added ",Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {e->
                            Log.e("TAG", "onCreate:$e " )
                        }

                    }
                    }
             }

             }
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        binding.imgHeart.setOnClickListener {
            if (!fill){
                fill=true
                val editor = pref.edit()
                editor.putBoolean("fill", fill)
                editor.apply()
               binding.imgHeart.setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite_24))
            }else{
                fill=false
                val editor = pref.edit()
                editor.putBoolean("fill", fill)
                editor.apply()
               binding.imgHeart.setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite_border_24))
            }
            val storageRef = storage!!.reference
            val mountainsRef = storageRef.child(dt.toString())
            binding.imageView5.isDrawingCacheEnabled = true
            binding.imageView5.buildDrawingCache()
            val bitmap = (binding.imageView5.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Log.e("TAG", "onCreate:fail " )
                // Handle unsuccessful uploads
            }.addOnCompleteListener { taskSnapshot ->
                Log.e("TAG", "onCreate:success " )
                if (taskSnapshot.isSuccessful){
                    mountainsRef.downloadUrl.addOnSuccessListener { task->
                        Log.e("TAG", "onCreate:$task " )

                        val productDetail=DataModelClass(img =task.toString(),lname=lname.toString(),rating=rating.toString(), description = pdetail.toString(),price=price.toString(), review =nuration.toString(), ftitle =name.toString(), kg = pc.toString()  )
                        FirebaseDatabase.getInstance().reference.child("FavouriteData").child(lname.toString()).setValue(productDetail).addOnCompleteListener {
                                task->
                            if (task.isSuccessful){
                                Toast.makeText(this,"Data added ",Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {e->
                            Log.e("TAG", "onCreate:$e " )
                        }

                    }
                }
            }
        }
    }

    override fun onResume() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val scoreData = sharedPreferences.getBoolean("fill",false)
        Log.e("TAG", "onResume:$scoreData " )
        if (scoreData==true){
           binding.imgHeart.setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite_24))
        }else{
           binding.imgHeart.setImageDrawable(resources.getDrawable(R.drawable.baseline_favorite_border_24))
        }
        super.onResume()
    }


}