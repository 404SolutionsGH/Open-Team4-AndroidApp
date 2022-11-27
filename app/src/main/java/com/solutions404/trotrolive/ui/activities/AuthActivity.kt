package com.solutions404.trotrolive.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.solutions404.trotrolive.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var authBinding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(authBinding.root)
    }
}