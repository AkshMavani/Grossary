package com.example.multityperecycleview


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.groceriesapp.R
import com.example.groceriesapp.activity.ActivityData
import com.example.groceriesapp.utils.DataModelClass
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream


class CustomAdapter(private val mList: List<ChildModelClass>,val context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var storage: FirebaseStorage?=null
    private var img: Uri?=null
    var price:String?=null
    var ftitle:String?=null
    var pc:String?=null
    var lname:String?=null
    var pdetail:String?=null
    var nuration:String?=null
    var rating:String?=null





    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.child_rc_layout, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        price=ItemsViewModel.price
        ftitle=ItemsViewModel.ftitle
        pc=ItemsViewModel.pc
        lname=ItemsViewModel.lname
        pdetail=ItemsViewModel.pdetail
        nuration=ItemsViewModel.nuration
        rating=ItemsViewModel.rating


        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.images)
        holder.tv.text=ItemsViewModel.price+"$"
        holder.tv1.text=ItemsViewModel.pc
        holder.tv2.text=ItemsViewModel.ftitle


        // sets the text to the textview from our itemHolder class
        holder.itemView.setOnClickListener {
            Log.e("TAG", "onBindViewHolder: $position")
            val intent=Intent(it.context,ActivityData::class.java)
            intent.putExtra("images",ItemsViewModel.images)
            intent.putExtra("name",ItemsViewModel.ftitle)
            intent.putExtra("price",ItemsViewModel.price)
            intent.putExtra("lname",ItemsViewModel.lname)
            intent.putExtra("nuration",ItemsViewModel.nuration)
            intent.putExtra("rating",ItemsViewModel.rating)
            intent.putExtra("pdetail",ItemsViewModel.pdetail)
            intent.putExtra("pc",ItemsViewModel.pc)


            it.context.startActivity(intent)
        }
        storage = FirebaseStorage.getInstance()
        holder.btn.setOnClickListener {
            val storageRef = storage!!.reference
            val mountainsRef = storageRef.child(ItemsViewModel.images.toString())
            holder.imageView.isDrawingCacheEnabled = true
            holder.imageView.buildDrawingCache()
            val bitmap = ( holder.imageView.drawable as BitmapDrawable).bitmap
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val data = baos.toByteArray()

            var uploadTask = mountainsRef.putBytes(data)
            uploadTask.addOnFailureListener {
                Log.e("TAG", "onCreate:fail " )
                // Handle unsuccessful uploads
            }.addOnCompleteListener { taskSnapshot ->
                Log.e("TAG", "onCreate:success")
                if (taskSnapshot.isSuccessful){
                    mountainsRef.downloadUrl.addOnSuccessListener { task->
                        Log.e("TAG", "onCreate:$task " )
                        val productDetail=DataModelClass(img =task.toString(),lname=ItemsViewModel.lname,rating=ItemsViewModel.rating, description = ItemsViewModel.pdetail,price=ItemsViewModel.price, review =ItemsViewModel.nuration, ftitle=ItemsViewModel.ftitle, kg = ItemsViewModel.pc)
                        FirebaseDatabase.getInstance().reference.child("data").child(ItemsViewModel.ftitle).setValue(productDetail).addOnCompleteListener {
                                task->
                            if (task.isSuccessful){
                                Toast.makeText(context,"Data added ",Toast.LENGTH_SHORT).show()
                            }
                        }.addOnFailureListener {e->
                            Log.e("TAG", "onCreate:$e " )
                        }
                    }
                }
            }

        }

    }


    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.ig)
        val tv:TextView=itemView.findViewById(R.id.tvPrice)
        val tv1:TextView=itemView.findViewById(R.id.TvPics)
        val tv2:TextView=itemView.findViewById(R.id.TvNAme)
        val btn:FloatingActionButton=itemView.findViewById(R.id.floatingActionBtn)
    }

}