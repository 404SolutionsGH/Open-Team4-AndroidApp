package com.koose.open_team4androidapp.Screens

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.adapters.OnboardingItem
import com.koose.open_team4androidapp.adapters.OnboardingItemsAdapter
import com.koose.open_team4androidapp.databinding.ActivityMainBinding


class OnboardingScreen : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicators(0)
    }


    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.lagos,
                    title = "Know Your Trotro Fares",
                    description= "You can search any place nearby or with-in the specified city." +
                            " We will display specific or all related" +
                            " trotro Fares to match your bus stops."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.honestdriver,
                    title = "Report On Missing Items?",
                    description= "We provide almost all the numbers of all stations and landmarks" +
                            " registered with us. " +
                            "You can easily redeem found items as well."
                ),

                OnboardingItem(
                    onboardingImage = R.drawable.kaneshi,
                    title = "Add Missing Bus Stop",
                    description= "If you have a station or have fleet of Minivans " +
                            "a part of our growing industry with (EU, GPRTU, AMA, Google)" +
                            ",add your profile by following simple steps."
                ),

                OnboardingItem(
                    onboardingImage = R.drawable.chosenOne,
                    title = "DigiLigc Smart Logistics Winner Eu Funded Project",
                    description= "We will handle everything for you. " +
                            "As you have the app in your pocket then you don\\'t " +
                            "have to worry about anything. "
                ),
            )
        )

        binding.slider.adapter = onboardingItemsAdapter

    }


    private fun setCurrentIndicators(i: Int) {

    }

    private fun setupIndicators() {
        TODO("Not yet implemented")
    }



}