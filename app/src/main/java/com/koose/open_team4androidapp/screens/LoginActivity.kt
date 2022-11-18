package com.koose.open_team4androidapp.screens

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.koose.open_team4androidapp.firestore.FireStoreClass
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityLoginBinding
import com.koose.open_team4androidapp.models.User

@Suppress("DEPRECATION")
class LoginActivity : BaseActivity(), View.OnClickListener {

    private lateinit var lbinding:ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lbinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(lbinding.root)


        // This is used to hide the status bar and make

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        //click for forget PassWord
        lbinding.tvForgotPassword.setOnClickListener(this)

        //When you want to login
        lbinding.btnLogin.setOnClickListener(this)

        //Register New Account
        lbinding.tvRegister.setOnClickListener(this)


    }

    fun userLoggedInSuccess(user: User){
        //hide Progress dialog

        Log.i("first name", user.firstName)
        Log.i("last name", user.lastName)
        Log.i("email", user.email)

        //Redict the user to Main Screen after Log In
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onClick(view: View?) {
        if(view != null){
            when (view.id){
                R.id.tv_forgot_password ->{
                    val intent = Intent(this, ForgotPasswordActivity::class.java)
                    startActivity(intent)

                }
                R.id.btn_login -> {

                    //Validate function
                    loginRegisteredUser()
                }
                R.id.tv_register -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun validateLoginDetails():Boolean{
        return when{
            TextUtils.isEmpty(lbinding.etEmail.text.toString().trim{it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
                false
            }

            TextUtils.isEmpty(lbinding.etPassword.text.toString().trim{it <= ' '})->{
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }else ->{
                true
            }
        }
    }

    private fun loginRegisteredUser() {

        if (validateLoginDetails()) {

            //Show Progress With A TextView
            showProgressDialog(resources.getString(R.string.please_wait))

            val email: String = lbinding.etEmail.text.toString().trim { it <= ' ' }
            val password: String = lbinding.etPassword.text.toString().trim { it <= ' ' }

            //Create an instance and Login a registeredUser with email and password
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    //Hide The Progress Dialog
                    /*      hideProgressDialog()
      */
                    if (task.isSuccessful) {
                        FireStoreClass().getUserDetails(this, )

                    } else {
                        //hide Progress dialog
                        hideProgressDialog()
                        showErrorSnackBar(task.exception!!.message.toString(), true)
                    }
                }
        }
    }

}