package com.example.groceriesapp.utils

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceriesapp.R
import com.example.multityperecycleview.CustomAdapter

import com.example.multityperecycleview.ParentModelClass


class ParantAdapter(private val mList: ArrayList<ParentModelClass>, val context: Context) : RecyclerView.Adapter<ParantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_rc_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        holder.title.text=mList.get(position).title
        val childAdapter= CustomAdapter(mList.get(position).childModelClass!!,context)
        holder.rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.rc.adapter = childAdapter


        holder.SeeAll.setOnClickListener {
            Log.e("TAG", "SeeAll:${ItemsViewModel.childModelClass} " )
            val childAdapter= CustomAdapter(mList.get(position).childModelClass!!,context)
           holder.rc.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
           holder.rc.layoutManager = GridLayoutManager(context,2)
            holder.rc.adapter = childAdapter
            Log.e("TAG", "childadapter$childAdapter " )
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val rc=itemView.findViewById<RecyclerView>(R.id.rv_parent)
        val title=itemView.findViewById<TextView>(R.id.tvTitle)
        val SeeAll=itemView.findViewById<TextView>(R.id.TvSeeAll)

    }
}