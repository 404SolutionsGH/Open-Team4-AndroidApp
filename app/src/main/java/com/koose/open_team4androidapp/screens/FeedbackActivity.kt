package com.koose.open_team4androidapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.adapters.feedAdapter
import com.koose.open_team4androidapp.databinding.ActivityFeedbackBinding
import com.koose.open_team4androidapp.models.feedback

class FeedbackActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var addBn: Button
    private lateinit var toolbar: Toolbar
    private lateinit var Feed: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)
        addBn = findViewById(R.id.add)
        Feed = findViewById(R.id.Feed)
        recyclerView = findViewById(R.id.recViewf)

        toggle = ActionBarDrawerToggle(this,binding.drawerLf,toolbar,R.string.openDrawer,R.string.closeDrawer)
        binding.drawerLf.addDrawerListener(toggle)
        toggle.syncState()

        val feedbackList = ArrayList<feedback>()

        addBn.setOnClickListener{
            if(Feed.text.isNullOrBlank()){
                Toast.makeText(this, "Please provide feedback", Toast.LENGTH_SHORT).show()
            }
            else{
                Feed.setText("")
                feedbackList.add(
                    feedback(Feed.text.toString())
                )
                val adapter = feedAdapter(this,feedbackList)
                recyclerView.adapter = adapter
            }
        }

    }
}