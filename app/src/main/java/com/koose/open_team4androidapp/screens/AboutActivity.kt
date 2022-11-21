package com.koose.open_team4androidapp.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = findViewById(R.id.toolbar)

        //drawer toggle
        toggle = ActionBarDrawerToggle(this,binding.drawerLA,toolbar,R.string.openDrawer,R.string.closeDrawer)
        binding.drawerLA.addDrawerListener(toggle)
        toggle.syncState()

        //switching activity with drawer

        //switching activity with drawer
        binding.navViewA.setNavigationItemSelectedListener {
            it.isChecked = true

            when(it.itemId){
                R.id.home-> {val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)}
                R.id.ticket-> {val intent = Intent(this, TicketActivity::class.java)
                    startActivity(intent)}
                R.id.about-> {val intent = Intent(this, AboutActivity::class.java)
                    startActivity(intent)}
                R.id.cart-> {val intent = Intent(this, CartActivity::class.java)
                    startActivity(intent)}
                R.id.profile-> {val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)}
            }

            true
        }

    }
}