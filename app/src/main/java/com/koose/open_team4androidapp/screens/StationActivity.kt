package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityStationBinding

class StationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStationBinding
    private lateinit var stationImg: ImageView
    private lateinit var name: TextView
    private lateinit var from : TextView
    private lateinit var to : TextView
    private lateinit var date: TextView
    private lateinit var type: TextView
    private lateinit var fare: TextView
    private lateinit var time: TextView
    private lateinit var  disabled: AutoCompleteTextView
    private lateinit var pregnant: AutoCompleteTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station)

        binding = ActivityStationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stationImg = findViewById(R.id.shuttleImg)
        name = findViewById(R.id.name)
        from = findViewById(R.id.from_loc)
        to = findViewById(R.id.to_loc)
        date = findViewById(R.id.date)
        type = findViewById(R.id.type)
        fare = findViewById(R.id.fare)
        disabled = findViewById(R.id.disabled)
        pregnant = findViewById(R.id.pregnant)
        time = findViewById(R.id.time)


        //getting intent data
        val intent = getIntent();
        val i_shuttleImg = intent.getIntExtra("shuttleimg",0)
        val i_name = intent.getStringExtra("name")
        val i_from = intent.getStringExtra("fromLoc")
        val i_to = intent.getStringExtra("toLoc")
        val i_date = intent.getStringExtra("date")
        val i_type = intent.getStringExtra("type")
        val i_fare  = intent.getStringExtra("fare")
        val i_time  = intent.getStringExtra("time")

        //using intent data
        stationImg.setImageResource(i_shuttleImg)
        name.text = i_name
        from.text = i_from
        to.text = i_to
        date.text = i_date
        type.text = i_type
        fare.text = i_fare
        time.text = i_time



        //implementing drop down list for disabled/pregnant
        val care = resources.getStringArray(R.array.care)
        val careArrayAdapter = ArrayAdapter(this, R.layout.drop_down_item, care)
        binding.disabled.setAdapter(careArrayAdapter)
        binding.pregnant.setAdapter(careArrayAdapter)


        binding.confirmBn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("disabled", disabled.text.toString())
            intent.putExtra("pregnant", pregnant.text.toString())
            intent.putExtra("fare", fare.text)
            intent.putExtra("img", i_shuttleImg)
            startActivity(intent)
        }
    }
}