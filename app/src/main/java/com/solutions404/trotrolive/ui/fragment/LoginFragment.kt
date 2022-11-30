package com.solutions404.trotrolive.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var loginBn: Button
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText

    private var email = ""
    private var password = ""

    //progress dialog
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        //checking is user is logged in before creating view
        checkUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

}