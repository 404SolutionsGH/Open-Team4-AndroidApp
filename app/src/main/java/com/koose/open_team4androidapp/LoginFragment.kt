package com.koose.open_team4androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.koose.open_team4androidapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var loginBinding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var btnLogin: Button
    private lateinit var eteMail: EditText
    private lateinit var etPassword: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)


        btnLogin = loginBinding.loginButton
        eteMail = loginBinding.etEmailAddress
        etPassword = loginBinding.etPassword

        auth = FirebaseAuth.getInstance()
        loginBinding.signupText.setOnClickListener {
            onClick()
        }
        loginBinding.loginButton.setOnClickListener {
            login()
        }

        return loginBinding.root

    }

    private fun login() {
        val email = eteMail.text.toString()
        val password = etPassword.text.toString()

        if (email.isBlank() || password.isBlank() ){
            Toast.makeText(requireActivity(), "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }


        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
            if (task.isSuccessful){
                Toast.makeText(requireActivity(), "Successfully Login ", Toast.LENGTH_SHORT).show()
            }
        }.addOnCompleteListener { exception ->
            Toast.makeText(requireActivity(), exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onClick() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        loginBinding
    }


}