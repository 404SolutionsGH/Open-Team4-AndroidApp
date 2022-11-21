package com.koose.open_team4androidapp.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.koose.open_team4androidapp.databinding.ActivityProfileBinding

class ProfileActivity : BaseActivity() {

    private lateinit var binding: ActivityProfileBinding
    //action bar
    private lateinit var actionBar: ActionBar
    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


        //handle click home
        binding.homebn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.logo.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun checkUser() {
        //check if user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            //user is already logged in, get user info
            //get user
            val email = firebaseUser.email
            //set to text view
            binding.emailTv.text = email
        }
        else{
            //user is not logged in
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }
}