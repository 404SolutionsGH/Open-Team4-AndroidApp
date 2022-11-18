package com.koose.open_team4androidapp.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.koose.open_team4androidapp.R
import com.koose.open_team4androidapp.databinding.ActivityLoginBinding
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

    }

    override fun onClick(view: View?) {
        if(view != null){
            when (view.id){
                R.id. ->{
                    val intent = Intent(this, ForgetPasswordActivity::class.java)
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

}