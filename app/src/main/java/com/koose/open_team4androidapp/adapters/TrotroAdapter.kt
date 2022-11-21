package com.koose.open_team4androidapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.models.TrotroData

class TrotroAdapter(private val context: Context,
                    private val BookingList:ArrayList<TrotroData>,
                    private val handleShuttleClick:  HandleTrotroClick):RecyclerView.Adapter<TrotroAdapter.TrotroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrotroViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.station_list_item, parent,false)
        return TrotroViewHolder(rootView)
    }

    interface HandleTrotroClick{
        fun onLearnerClick(position: Int)
    }

    override fun onBindViewHolder(holder: TrotroViewHolder, position: Int) {
        val dataPosition = BookingList[position]

        holder.name.text = dataPosition.name
        holder.fromLoc.text = dataPosition.fromLoc
        holder.toLoc.text = dataPosition.toLoc
        holder.date.text = dataPosition.date
        holder.time.text = dataPosition.time
        holder.type.text = dataPosition.Agency

        holder.learnersClick.setOnClickListener{
            handleShuttleClick.onLearnerClick(position)
        }

        Glide
            .with(context)
            .load(dataPosition.img)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.imgUrl);    }

    override fun getItemCount(): Int {
        return BookingList.size
    }


    class TrotroViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.listName)
        val imgUrl: ImageView = itemView.findViewById(R.id.shuttleItemImg)
        val fromLoc: TextView = itemView.findViewById(R.id.listFromLoc)
        val toLoc: TextView = itemView.findViewById(R.id.listToLoc)
        val date: TextView = itemView.findViewById(R.id.listDate)
        val time: TextView = itemView.findViewById(R.id.listTime)
        val type: TextView = itemView.findViewById(R.id.listType)
        val learnersClick: CardView = itemView.findViewById(R.id.booking_item)


    }
}