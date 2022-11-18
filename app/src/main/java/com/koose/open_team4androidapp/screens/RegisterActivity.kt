package com.koose.open_team4androidapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityMainBinding
import com.koose.open_team4androidapp.databinding.ActivityRegisterBinding

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

        setupActionBar()

    }

    private fun setupActionBar() {
        setSupportActionBar(binding.toolbarRegisterActivity)

        val  actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_backpress)
        }

        binding.toolbarRegisterActivity.setNavigationOnClickListener { onBackPressed() }
    }

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

    fun userRegistrationSuccess() {
        //first i want to run the function in the background so we need progresss dialog
        hideProgressDialog()
    }
}