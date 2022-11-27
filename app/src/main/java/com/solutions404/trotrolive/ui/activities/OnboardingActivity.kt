package com.solutions404.trotrolive.ui.activities

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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

    }
}