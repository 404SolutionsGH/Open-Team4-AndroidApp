package com.solutions404.trotrolive.ui.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.constants.OnBoardingItems.onBoardingAdapter
import com.solutions404.trotrolive.databinding.ActivityOnboardingBinding

class OnboardingActivity : Activity() {

    private lateinit var onboardingBinding: ActivityOnboardingBinding

    private var isContentLoaded = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onboardingBinding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(onboardingBinding.root)

        installSplashScreen()

        // remove status
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }




        //TODO(Making onBoarding Screen show up only on first time install)
        val preferences: SharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
        val firstTime: String? = preferences.getString("FirstTimeInstall", "")


        if (firstTime.equals("Yes", true)){
            //If app is not opened for the first time, it launches the main activity
            val launchAuth = Intent(this, AuthActivity::class.java)
            startActivity(launchAuth)
        }else{
            //If app is opened for the first time, it launches the onBoarding activity and sets the FirstTimeInstall preference to yes
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.putString("FirstTimeInstall", "Yes")
            editor.apply()
        }

        //Done

        onboardingBinding.vpOnboardingSlider.adapter = onBoardingAdapter
        setupIndicators()
        setCurrentIndicators(0)
        onboardingBinding.vpOnboardingSlider.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        })
        //handle next button click
        onboardingBinding.btnOnboardingNext.setOnClickListener {
            if (onboardingBinding.vpOnboardingSlider.currentItem + 1 < onBoardingAdapter.itemCount) {
                onboardingBinding.vpOnboardingSlider.currentItem += 1
            } else {
                Intent(applicationContext, AuthActivity::class.java).also {
                    startActivity(it)
                }
            }
        }

        // skip the onboarding slides
        onboardingBinding.tvStartNow.setOnClickListener {
            Intent(applicationContext, AuthActivity::class.java).also {
                startActivity(it)
            }
        }

        //
        setupSplashScreen()

    }

    /**
     * setupSplashScreen
     */
    private fun setupSplashScreen() {
        // keep the splash for longer period -> say checking internet status/speed/, db setup

        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isContentLoaded) {// Check if the initial data is ready.
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else false  // hold on to the splash
                }
            }
        )
    }


    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_onboarding
                    )
                )
                this?.layoutParams = layoutParams
            }
            onboardingBinding.llOnboardingIndicators.addView(indicators[i])

        }
    }

    /**
     *
     */
    private fun setCurrentIndicators(index: Int) {
        val childCount = onboardingBinding.llOnboardingIndicators.childCount
        for (i in 0 until childCount) {
            val imageView = onboardingBinding.llOnboardingIndicators[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_onboarding
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_onboarding
                    )
                )
            }
        }
    }
}