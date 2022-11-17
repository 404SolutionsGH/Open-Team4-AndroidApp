package com.koose.open_team4androidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.koose.open_team4androidapp.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private lateinit var registerBinding: FragmentRegisterBinding


    //create Firebase authentication Object
    private lateinit var auth: FirebaseAuth
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etPassword: EditText
    private lateinit var etCpassword: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    registerBinding = FragmentRegisterBinding.inflate(inflater, container, false)


        etFirstName = registerBinding.firstNameET
        etLastName = registerBinding.lastNameET
        etEmail = registerBinding.emailET
        etPhone = registerBinding.phoneET
        etPassword = registerBinding.passwordET
        etCpassword = registerBinding.confirmPasswordET


        registerBinding.loginPromptTV.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }

        registerBinding.signUpBn.setOnClickListener {
            signUpUser()
        }

        return registerBinding.root
    }

    private fun signUpUser() {
        val firstName = etFirstName.text.toString()
        val lastName = etLastName.text.toString()
        val email = etEmail.text.toString()
        val phone = etPhone.text.toString()
        val password = etPassword.text.toString()
        val cPassword = etCpassword.text.toString()


        //check first Name and other detail Not Blank
        if (firstName.isBlank() || lastName.isBlank() ||phone.isBlank()) {
            Toast.makeText(requireActivity(), "First and Last Name can't be blank bro", Toast.LENGTH_SHORT).show()
            return
        }
        if (email.isBlank() || password.isBlank() || cPassword.isBlank()){
            Toast.makeText(requireActivity(), "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (password != cPassword){
            Toast.makeText(requireActivity(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }

        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){

                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToMainFeed())

                Toast.makeText(requireActivity(), "Successfully Signed Up", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireActivity(), "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}