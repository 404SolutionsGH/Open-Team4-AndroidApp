package com.koose.open_team4androidapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.koose.open_team4androidapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var loginBinding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)

        loginBinding.signupText.setOnClickListener {
            onClick()
        }

        return loginBinding.root

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