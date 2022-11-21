package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityPaymentBinding

class PaymentActivity : AppCompatActivity() {

    private lateinit var tvPreg: TextView
    private lateinit var tvDisa: TextView
    private lateinit var tvFare: TextView
    private lateinit var binding:ActivityPaymentBinding
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val pregnant = intent.getStringExtra("pregnant")
        val disabled = intent.getStringExtra("disabled")
        val fare = intent.getStringExtra("fare")
        val img = intent.getIntExtra("img",0)

        tvPreg.text = pregnant
        tvDisa.text = disabled
        tvFare.text = fare
        image.setImageResource(img)


        binding.confirmPayBn.setOnClickListener{
            val intent = Intent(this, PaymentMethActivity::class.java)
            intent.putExtra("fare",tvFare.text)
            startActivity(intent)
        }

    }
}