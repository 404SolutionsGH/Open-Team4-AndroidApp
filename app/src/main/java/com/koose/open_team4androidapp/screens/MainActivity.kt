package com.koose.open_team4androidapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.koose.open_team4androidapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }


    override fun onSupportNavigateUp(): Boolean {// Up button will work on this method
        return Navigation.findNavController(this, R.id.my_nav_host_fragment).navigateUp()
    }
}