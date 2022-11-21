package com.koose.open_team4androidapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.models.feedback

class feedAdapter(private val context: Context, private val feedList: ArrayList<feedback>):
    RecyclerView.Adapter<feedAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_design, parent, false)
        return ImageViewHolder(view)    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageModel = feedList[position]
        holder.tvFeed.text = imageModel.feedback    }

    override fun getItemCount(): Int {
        return feedList.size    }

    class ImageViewHolder (ItemView: View) : RecyclerView.ViewHolder(ItemView){
        val tvFeed: TextView = itemView.findViewById(R.id.tvFeed)

    }
}