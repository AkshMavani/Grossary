package com.example.groceriesapp.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.groceriesapp.R
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(context: Context?, sliderDataArrayList: ArrayList<SliderData>) :
    SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder>() {
    // list for storing urls of images.
    private val mSliderItems: List<SliderData>

    // Constructor
    init {
        mSliderItems = sliderDataArrayList
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, null)
        return SliderAdapterViewHolder(inflate)
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    override fun onBindViewHolder(viewHolder: SliderAdapterViewHolder, position: Int) {
        val (imgUrl) = mSliderItems[position]

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
            .load(imgUrl)
            .fitCenter()
            .into(viewHolder.imageViewBackground)
    }

    // this method will return
    // the count of our list.
    override fun getCount(): Int {
        return mSliderItems.size
    }

    class SliderAdapterViewHolder(itemView: View) :
        ViewHolder(itemView) {
        // Adapter class for initializing
        // the views of our slider view.
        lateinit var ItemView: View
        var imageViewBackground: ImageView

        init {
            imageViewBackground = itemView.findViewById<ImageView>(R.id.myimage)
            ItemView = itemView
        }
    }
}