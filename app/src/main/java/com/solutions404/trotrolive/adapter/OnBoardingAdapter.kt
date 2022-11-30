package com.solutions404.trotrolive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.viewModel.OnboardingItemViewModel

class OnBoardingAdapter(  private val onboardingSlide: List<OnboardingItemViewModel>):
    RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_list_container_onboarding,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(onboardingSlide[position])
    }

    override fun getItemCount(): Int {
        return onboardingSlide.size
    }



   inner class OnboardingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val onboardingDescription: TextView = itemView.findViewById(R.id.tv_onboardingDescription)
        private val slideImage: ConstraintLayout = itemView.findViewById(R.id.cl_onboardingSlide)
        fun bind(onboardingSlide: OnboardingItemViewModel) {
            onboardingDescription.text = onboardingSlide.description
            slideImage.setBackgroundResource(onboardingSlide.image)
        }

    }
}