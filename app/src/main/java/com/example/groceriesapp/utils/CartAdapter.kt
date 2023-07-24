package com.example.groceriesapp.utils

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.groceriesapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso


class CartAdapter(private val mList: ArrayList<DataModelClass>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
   var total=1
   var totalprice=0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_view_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        Picasso.get().load(mList[position].img).placeholder(R.mipmap.ic_launcher).fit().into(holder.imageView)
        holder.tvName.text = ItemsViewModel.ftitle
        holder.tvKg.text = ItemsViewModel.kg
        holder.tvTotalPrice.text=ItemsViewModel.price+"$"

        holder.close.setOnClickListener {
            mList.removeAt(position)
            notifyDataSetChanged()
            FirebaseDatabase.getInstance().reference.child("data").child(ItemsViewModel.ftitle.toString()).removeValue().addOnCompleteListener { task->
                if (task.isSuccessful){
                    Toast.makeText(it.context,"deleted",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e->
                Log.e("TAG", "onBindViewHolder:$e ")
            }
        }
        holder.imgMinus.setOnClickListener {
            if (total>1) {
                total--
                holder.txtNum.text = total.toString()
                totalprice= ItemsViewModel.price!!.toInt() * total
                holder.tvTotalPrice.text=totalprice.toString()+"$"
            }
        }
        holder.imgPlus.setOnClickListener {
            total++
            holder.txtNum.text = total.toString()
            totalprice= ItemsViewModel.price!!.toInt() * total
            holder.tvTotalPrice.text=totalprice.toString()+"$"
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgCart)
        val close: ImageView = itemView.findViewById(R.id.igClose)
        val tvName: TextView = itemView.findViewById(R.id.tvCartDetail)
        val tvKg: TextView = itemView.findViewById(R.id.tvkg)
        val tvTotalPrice: TextView = itemView.findViewById(R.id.tvTotalPrice)
        val imgMinus: ImageView = itemView.findViewById(R.id.igCartMinus)
        val imgPlus: ImageView = itemView.findViewById(R.id.igCartPlus)
        val txtNum: TextView = itemView.findViewById(R.id.txtCartText)









    }
}