package com.koose.open_team4androidapp.Firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.koose.open_team4androidapp.models.User
import com.koose.open_team4androidapp.screens.BaseActivity
import com.koose.open_team4androidapp.screens.LoginActivity
import com.koose.open_team4androidapp.screens.RegisterActivity
import com.koose.open_team4androidapp.utils.Constants
import io.grpc.InternalChannelz.id

class FireStoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo:User) {
        //The User is collection name
        mFireStore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegistrationSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error While Registering the User"
                )
            }
    }

    fun getCurrentUserID():String{
        //An instance of CreateUser using FirebaseAuth
        val currentUser = FirebaseAuth.getInstance().currentUser

        //current userID if not null, it be null
        var currentUserID = " "
        if (currentUser != null){
            currentUserID = currentUser.uid
        }

        return  currentUserID
    }


    fun getUserDetails(activity: Activity){

        //The collection we want the data from
        mFireStore.collection(Constants.USERS)
            //document id to get the Field of the user
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->

                Log.i(activity.javaClass.simpleName, document.toString())

                //here we have to receive snapshot into user Model
                val user = document.toObject(User::class.java)!!

                /** TO do pass the result to the Log**/

                //Storing User Data on SharedPreference

                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.TROTROLIVE_PREFERENCES,
                        Context.MODE_PRIVATE
                    )

                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_USERNAME,
                    "${user.firstName} ${user.lastName}"
                )

                //Apply editor
                editor.apply()

                when(activity){
                    is LoginActivity ->{
                        //call a fun of baseActivity for transferring the result to it
                        activity.userLoggedInSuccess(user)
                    }
                }
                //END
            }
            .addOnFailureListener { e ->
                //Hide The Progress dialog if there is any error,
                when(activity){
                    is LoginActivity ->{
                        activity.hideProgressDialog()
                    }
                }

            }
    }

}