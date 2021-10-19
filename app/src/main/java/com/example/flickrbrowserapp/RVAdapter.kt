package com.example.flickrbrowserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter(val photos:ArrayList<Photo>,val activity: MainActivity):RecyclerView.Adapter<RVAdapter.RVHolder>() {
    class RVHolder (itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVHolder {
        return RVHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_row,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RVHolder, position: Int) {
        val server=photos[position].server
        val id=photos[position].id
        val secret=photos[position].secret
        val title=photos[position].title
        val imageUrl="https://farm66.staticflickr.com/$server/${id}_${secret}.jpg"

        holder.itemView.apply {
            tvImage.text=title
         Glide.with(activity)
            .load(imageUrl)
             .centerCrop()
            .into(imageView)
            imageView.setOnClickListener {
                Glide.with(activity)
                    .load(imageUrl)
                    .centerCrop()
                    .into(activity.imageViewFullScreen)
                activity.imageViewFullScreen.isVisible=true
                activity.linearLayout.isVisible=false
            }
            activity.imageViewFullScreen.setOnClickListener {
                activity.imageViewFullScreen.isVisible=false
                activity.linearLayout.isVisible=true


            }
        }


    }

    override fun getItemCount()=photos.size
}