package com.solutions404.trotrolive.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solutions404.trotrolive.database.trotro.Trotro
import com.solutions404.trotrolive.databinding.TrotroStopItemBinding


class TrotroStopAdapter(
    private val onItemClicked: (Trotro) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Trotro, TrotroStopAdapter.TrotroStopViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Trotro>() {
            override fun areItemsTheSame(oldItem: Trotro, newItem: Trotro): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Trotro, newItem: Trotro): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrotroStopViewHolder {
        val viewHolder = TrotroStopViewHolder(
            TrotroStopItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TrotroStopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrotroStopViewHolder(
        private var binding: TrotroStopItemBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(trotro: Trotro) {
            binding.stopNameTextView.text = trotro.stopName
            binding.stationsTextView.text = trotro.stationName
            binding.fareGHS.text = trotro.fareGhs.toString()
        }
    }
}
