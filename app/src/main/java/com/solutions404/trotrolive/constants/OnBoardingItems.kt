package com.solutions404.trotrolive.constants

import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.adapter.OnBoardingAdapter
import com.solutions404.trotrolive.viewModel.OnboardingItemViewModel

object OnBoardingItems {

    val onBoardingAdapter = OnBoardingAdapter(
        listOf(
            OnboardingItemViewModel(
                "You can search any place nearby or with-in the specified city.\" +\n" +
                        " \" We will display specific or all related\" +\n" +
                        "  \" trotro Fares to match your bus stops.\"",
                        R.drawable.ghanafirst,
            ),
            OnboardingItemViewModel(
                "\"We provide almost all the numbers of all stations and landmarks\" +\n" +
                        " \" registered with us. \" +\n" +
                        " \"You can easily redeem found items as well.\"",
                        R.drawable.honestdriver
            ),
            OnboardingItemViewModel(
                "We will handle everything for you. " +
                        "As you have the app in your pocket then you don\\'t " +
                        "have to worry about anything. ", R.drawable.chosenone
            )
        )
    )
}