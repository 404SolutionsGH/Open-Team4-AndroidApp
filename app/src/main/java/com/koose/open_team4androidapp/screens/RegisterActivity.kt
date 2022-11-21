package com.koose.open_team4androidapp.screens

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityRegisterBinding


@Suppress("DEPRECATION")
class RegisterActivity : BaseActivity() {

    //firebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

//        setupActionBar()

        binding.loginPromptTV.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)        }
        binding.signUpBn.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        //get data
        email = binding.emailET.text.toString().trim()
        password = binding.passwordET.text.toString().trim()
        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid format
            binding.emailET.error = "Invalid email"
        }
        else if(TextUtils.isEmpty(password)){
            //invalid format
            binding.passwordET.error = "Please enter password"
        }
        else if(password.length <6){
            //password length 6 char
            binding.passwordET.error = "Password must be atleast 6 characters long"
        }
        else{
            //data is valid, sign up
            firebaseSignUp()
        }



    }

    private fun firebaseSignUp() {
        //show progress
       showProgressDialog("Please wait")

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //signup success
                hideProgressDialog()
                //get current user
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this,"Account created with email $email", Toast.LENGTH_SHORT).show()

                //open profile
                startActivity(Intent(this,ProfileActivity::class.java))
                finish()
            }
            .addOnFailureListener{e ->
                Toast.makeText(this,"SignUp failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }

    }


}