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
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso

class FavouriteAdapter(private val mList: ArrayList<DataModelClass>) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {
    var total=1
    var totalprice=0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_view_layout, parent, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        Picasso.get().load(mList[position].img).placeholder(R.mipmap.ic_launcher).fit().into(holder.imageView)
     holder.tvName.text=ItemsViewModel.ftitle
        holder.tvml.text=ItemsViewModel.kg
        holder.tvTotalPrice.text=ItemsViewModel.price

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgFavourite)
        val tvName: TextView = itemView.findViewById(R.id.tvFavouriteName)
        val tvml: TextView = itemView.findViewById(R.id.tvml)
        val tvTotalPrice: TextView = itemView.findViewById(R.id.tvFavouritePrice)


    }
}