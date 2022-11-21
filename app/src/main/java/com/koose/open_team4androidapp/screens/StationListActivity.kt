package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.adapters.TrotroAdapter
import com.koose.open_team4androidapp.models.TrotroData
import com.koose.open_team4androidapp.utils.TrotroImg.stationimg
import com.koose.open_team4androidapp.utils.TrotroImg.stationimg2

class StationListActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_station_list)

        recyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val intent = getIntent();
        val From = intent.getStringExtra("From")
        val To = intent.getStringExtra("To")
        val Date = intent.getStringExtra("Date")

        val StationList = ArrayList<TrotroData>()

        StationList.add(
            TrotroData("Station 01","From: $From","To: $To","Date: $Date","Fare: GHc 8.00",
                "Time: 10am",0,0,false,false,"Type: ConcernTaxis",stationimg)
        )
        StationList.add(
            TrotroData("Station 02","From: $From","To: $To","Date: $Date","Fare: GHc 10.00",
                "Time: 10am",0,0,false,false,"Type: 2MExpress",stationimg)
        )
        StationList.add(
            TrotroData("Station 03","From: $From","To: $To","Date: $Date","Fare: GHc 9.00",
                "Time: 10am",0,0,false,false,"Type: PROTOA",stationimg2)
        )
        StationList.add(
            TrotroData("Station 04","From: $From","To: $To","Date: $Date","Fare: GHc 11.00",
                "Time: 10am",0,0,false,false,"Type: GPRTU",stationimg2)
        )

        val adapter = TrotroAdapter(this,StationList, object : TrotroAdapter.HandleTrotroClick {
            override fun onLearnerClick(position: Int) {
                val shuttle = StationList[position]
                val name:String = shuttle.name
                val fromLoc: String = shuttle.fromLoc
                val toLoc: String = shuttle.toLoc
                val date :String = shuttle.date
                val time :String = shuttle.time
                val fare :String = shuttle.fare
                val type :String = shuttle.Agency
                val shuttleImg: Int = shuttle.img


                val intent = Intent(this@StationListActivity, StationActivity::class.java)
                intent.putExtra("name",name)
                intent.putExtra("fromLoc",fromLoc)
                intent.putExtra("toLoc",toLoc)
                intent.putExtra("date",date)
                intent.putExtra("time",time)
                intent.putExtra("fare",fare)
                intent.putExtra("type",type)
                intent.putExtra("shuttleimg",shuttleImg)
                startActivity(intent)
            }
        })

        recyclerView.adapter = adapter
    }
}