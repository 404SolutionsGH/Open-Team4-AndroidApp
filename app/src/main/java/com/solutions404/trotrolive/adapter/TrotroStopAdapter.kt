package com.solutions404.trotrolive.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.solutions404.trotrolive.database.trotro.Trotro
import com.solutions404.trotrolive.databinding.TrotroStopItemBinding
import com.solutions404.trotrolive.viewModel.OnboardingItemViewModel
import java.text.SimpleDateFormat

abstract class TrotroStopAdapter (  private val trotro: List<TrotroStopItemBinding>):
    RecyclerView.Adapter<TrotroStopAdapter.TrotroViewHolder>()  {



    class TrotroViewHolder {

    }

}
