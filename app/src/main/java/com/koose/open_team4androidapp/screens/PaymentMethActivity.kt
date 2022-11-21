package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityPaymentMethBinding

class PaymentMethActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentMethBinding
    private lateinit var tvFare: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_meth)

        binding = ActivityPaymentMethBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvFare = findViewById(R.id.pmfare)

        val intent = getIntent()
        val fare = intent.getStringExtra("fare")

        tvFare.text = fare


        binding.master.setOnClickListener{
            startActivity(Intent(this, SuccessActivity::class.java))
        }
        binding.momo.setOnClickListener{
            startActivity(Intent(this, SuccessActivity::class.java))
        }
        binding.visa.setOnClickListener{
            startActivity(Intent(this, SuccessActivity::class.java))
        }
        binding.paypal.setOnClickListener{
            startActivity(Intent(this, SuccessActivity::class.java))
        }
    }
}