package com.koose.open_team4androidapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var fbinding:ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fbinding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(fbinding.root)
       // setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(findViewById(R.id.toolbar_forget_activity))

        val  actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_backpress)
        }

        fbinding.toolbarForgetActivity.setNavigationOnClickListener { onBackPressed() }

        fbinding.btnSumbit.setOnClickListener {
            val email: String = fbinding.etEmail.text.toString().trim{it <= ' '}
            if (email.isEmpty()){
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            }else{
                showProgressDialog(resources.getString(R.string.please_wait))
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        hideProgressDialog()
                        if(task.isSuccessful){

                            //Show Toast Message and finish forgot activity to go back to login
                            Toast.makeText(
                                this,
                                resources.getString(R.string.email_sent_success),
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        }else
                        {
                            showErrorSnackBar(task.exception!!.message.toString(), true)
                        }
                    }
            }
        }    }
}