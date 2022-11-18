package com.koose.open_team4androidapp.screens

import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityRegisterBinding
import com.koose.open_team4androidapp.firestore.FireStoreClass
import com.koose.open_team4androidapp.models.User

@Suppress("DEPRECATION")
class RegisterActivity : BaseActivity() {



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
            onBackPressed()
        }
        binding.signUpBn.setOnClickListener {
            registerUser()
        }

    }

//    private fun setupActionBar() {
//        setSupportActionBar(binding.toolbarRegisterActivity)
//
//        val  actionBar = supportActionBar
//        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(true)
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_backpress)
//        }
//
//        binding.toolbarRegisterActivity.setNavigationOnClickListener { onBackPressed() }
//    }

    /**
     * A function to validate the entries of a new user*/

    private fun validateRegisterDetails():Boolean{
        return when{
            TextUtils.isEmpty(binding.firstNameET.text.toString().trim{it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }

            TextUtils.isEmpty(binding.lastNameET.text.toString().trim{it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_last_name), true)
                false
            }

            TextUtils.isEmpty(binding.emailET.text.toString().trim{it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.
                err_msg_enter_email), true)
                false
            }else ->{
                showErrorSnackBar(resources.getString(R.string.register), false)
                true
            }
        }
    }

    private fun registerUser(){
        //check with validate function if the entries are valid or not
        if(validateRegisterDetails()){
            //Show Progress With A TextView
            showProgressDialog(resources.getString(R.string.please_wait))

            val email: String = binding.emailET.text.toString().trim{it <= ' '}
            val password: String = binding.passwordET.text.toString().trim{it <= ' '}

            //Create an instance and create a register a user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    OnCompleteListener<AuthResult>{ task ->

                        //if the register is successful
                        if (task.isSuccessful){
                            //firebase registered user
                            val firebaseUser: FirebaseUser = task.result!!.user!!

                            val user = User(
                                firebaseUser.uid,
                                binding.firstNameET.text.toString().trim{it <= ' '},
                                binding.lastNameET.text.toString().trim{it <= ' '},
                                binding.emailET.text.toString().trim{it <= ' '},
                            )

                            FireStoreClass().registerUser(this, user)

                            //Send Them to Sign In to Login Again ! Beautiful
                            FirebaseAuth.getInstance().signOut()
                            finish()


                        }else{
                            hideProgressDialog()
                            //if the registration is not successful then show error message

                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }

                }
        }
    }

    fun userRegistrationSuccess() {
        //first i want to run the function in the background so we need progresss dialog
        hideProgressDialog()

        Toast.makeText(
            this,
            resources.getString(R.string.register_success),
            Toast.LENGTH_SHORT
        ).show()

    }
}