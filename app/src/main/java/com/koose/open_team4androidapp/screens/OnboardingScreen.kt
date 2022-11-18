package com.koose.open_team4androidapp.screens

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
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
                    onboardingImage = R.drawable.chosenone,
                    title = "DigiLigc Smart Logistics Winner Eu Funded Project",
                    description= "We will handle everything for you. " +
                            "As you have the app in your pocket then you don\\'t " +
                            "have to worry about anything. "
                ),
            )
        )

        binding.slider.adapter = onboardingItemsAdapter

        //onboardingViewPager.adapter = onboardingItemsAdapter
        binding.slider.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })
        (binding.slider.getChildAt(0)as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        binding.nextBtn.setOnClickListener {
            if (binding.slider.currentItem + 1< onboardingItemsAdapter.itemCount){
                binding.slider.currentItem += 1
            }else{
                navigateToHomeActivity()
            }
        }

        binding.skipBtn.setOnClickListener {
            navigateToHomeActivity()
        }
        binding.getStartedBtn.setOnClickListener {
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        finish()
    }


    private fun setupIndicators() {
    indicatorsContainer = binding.dots
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }



    private fun  setCurrentIndicators(position:Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if(i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                      applicationContext,
                      R.drawable.indicator_inactive
                    )
                )
            }else(
                    imageView.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext,
                            R.drawable.indicator_inactive
                        )
                    )
            )
        }
    }



}