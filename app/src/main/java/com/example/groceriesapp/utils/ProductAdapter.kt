package com.example.groceriesapp.utils

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceriesapp.R
import com.example.groceriesapp.activity.SeeAllActivity
import com.example.multityperecycleview.ChildModelClass

class ProductAdapter(private val mList: ArrayList<ProductModelClass>) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        holder.imageView.setImageResource(ItemsViewModel.images)
        holder.textView.text = ItemsViewModel.title


        holder.itemView.setOnClickListener {
            val intent=Intent(it.context,SeeAllActivity::class.java)
            intent.putExtra("position",position)
            it.context.startActivity(intent)
//            Log.e("TAG", "position:$position ", )
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }


    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imgproduct)
        val textView: TextView = itemView.findViewById(R.id.txtproduct)
        val cl: LinearLayout = itemView.findViewById(R.id.cl)

    }
}