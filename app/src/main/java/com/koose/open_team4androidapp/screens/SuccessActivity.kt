package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success)

        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.done.setOnClickListener{
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}