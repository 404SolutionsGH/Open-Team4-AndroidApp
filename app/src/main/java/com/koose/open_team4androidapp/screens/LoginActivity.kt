package com.koose.open_team4androidapp.screens

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

import com.koose.open_team4androidapp.databinding.ActivityLoginBinding

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(){

    private lateinit var lbinding:ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private var email = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lbinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(lbinding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser();

        lbinding.tvDonTHaveAnAccount.setOnClickListener {
            navigateToRegister()
        }
        lbinding.tvRegister.setOnClickListener {
            navigateToRegister()
        }

        lbinding.btnLogin.setOnClickListener {
            //before logging in, validate data
            validateData()
        }


    }

    private fun validateData() {
        //get data
        email = lbinding.etEmail.text.toString().trim()
        password = lbinding.etPassword.text.toString().trim()

        //validate data
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email format
            lbinding.etEmail.error = "Invalid email format"
        }
        else if(TextUtils.isEmpty(password)){
            //no password entered
            lbinding.etPassword.error = "Please enter password"
        }
        else{
            //data is validated, begin login
            firebaseLogin()
        }
    }

    private fun firebaseLogin() {
        //show progress
        showProgressDialog("Please")
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                hideProgressDialog()
                //get user info
                val firebaseUser = firebaseAuth.currentUser
                val email = firebaseUser!!.email
                Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            .addOnFailureListener{ e->
                hideProgressDialog()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun navigateToRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun checkUser() {
        //if user is already logged in, go to profile activity
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser != null){
            //user is already logged in
            startActivity(Intent(this,ProfileActivity::class.java))
            finish()
        }
    }

}