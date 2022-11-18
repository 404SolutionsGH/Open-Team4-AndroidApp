package com.koose.open_team4androidapp.screens

import android.app.Dialog
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.koose.open_team4androidapp.R
import org.w3c.dom.Text

open class BaseActivity: AppCompatActivity() {

    private lateinit var mProgressDailog: Dialog

    fun showErrorSnackBar(message:String, errorMessage:Boolean){
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)

        val snackbarView = snackBar.view

        if (errorMessage){
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    this, R.color.error
                )
            )
        }else{
            snackbarView.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.success
                )
            )
        }




    }

    fun showProgressDialog(text:String){
        mProgressDailog = Dialog(this)

        /*Set the screen content from a layout res
     * */

        mProgressDailog.setContentView(R.layout.dialog_progress)

        mProgressDailog.findViewById<TextView>(R.id.tv_progress_text).text = text

        mProgressDailog.setCancelable(false)
        mProgressDailog.setCanceledOnTouchOutside(false)
        mProgressDailog.show()
    }


    fun hideProgressDialog(){
        mProgressDailog.dismiss()
    }
}