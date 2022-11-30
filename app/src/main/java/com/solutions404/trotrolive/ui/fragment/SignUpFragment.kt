package com.solutions404.trotrolive.ui.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.databinding.FragmentSignUpBinding
import com.solutions404.trotrolive.ui.activities.MainActivity



class SignUpFragment : Fragment() {

    private lateinit var signUpBinding: FragmentSignUpBinding

    //create Firebase authentication Object
    private lateinit var auth: FirebaseAuth

    private lateinit var firstNameET: EditText
    private lateinit var lastNameET: EditText
    private lateinit var emailET: EditText
    private lateinit var phoneET: EditText
    private lateinit var passwordET: EditText
    private lateinit var passwordConfirmET: EditText

    private var email = ""
    private var password = ""
    private var passwordC = ""
    private var firstName = ""
    private var lastName = ""
    private var phone = ""

    //progress dialog
    private lateinit var progressDialog: ProgressDialog




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        signUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)

        firstNameET = signUpBinding.firstNameET
        lastNameET = signUpBinding.lastNameET
        emailET = signUpBinding.emailET
        phoneET = signUpBinding.phoneET
        passwordET = signUpBinding.passwordET
        passwordConfirmET = signUpBinding.confirmPasswordET

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Signing you up...")
        progressDialog.setCanceledOnTouchOutside(false)

        signUpBinding.loginPromptTV.setOnClickListener{
            findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
            )
        }

        signUpBinding.signUpBn.setOnClickListener {
            progressDialog.show()
            validateData()
        }

        signUpBinding.skipRegisterTV.setOnClickListener{
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        return signUpBinding.root
    }

    private fun validateData() {
        firstName = firstNameET.text.toString().trim()
        lastName = lastNameET.text.toString().trim()
        email = emailET.text.toString().trim()
        phone = phoneET.text.toString().trim()
        password = passwordET.text.toString().trim()
        passwordC = passwordConfirmET.text.toString().trim()


        //check first Name and other detail Not Blank
        if (email.isEmpty()){
            signUpBinding.emailTIL.isErrorEnabled = true;
            signUpBinding.emailTIL.error = getString(R.string.err_msg_email);
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signUpBinding.emailTIL.isErrorEnabled = true;
            signUpBinding.emailTIL.error = getString(R.string.err_msg_invalid_email);
        }
        else if (password.isEmpty()){
            signUpBinding.passwordTIL.isErrorEnabled = true;
            signUpBinding.passwordTIL.error = getString(R.string.err_msg_pass);
        }
        else if (password.length < 6){
            signUpBinding.passwordTIL.isErrorEnabled = true;
            signUpBinding.passwordTIL.error = getString(R.string.err_msg_pass_short);
        }
        else if (passwordC.isEmpty() || password != passwordC){
            signUpBinding.confirmPasswordTIL.isErrorEnabled = true;
            signUpBinding.confirmPasswordTIL.error = getString(R.string.err_msg_pass_match);
        }
        else{
            // If all credential are correct
            // We call createUserWithEmailAndPassword
            // using auth object and pass the
            // email and pass in it.
            progressDialog.show()
            signUpWithFirebase()
        }

        /*else if (firstName.isEmpty()){
            signUpBinding.firstNameTIL.isErrorEnabled = true;
            signUpBinding.firstNameTIL.error = getString(R.string.err_msg_pass);
        }
        else if (lastName.isEmpty()){
            signUpBinding.lastNameTIL.isErrorEnabled = true;
            signUpBinding.lastNameTIL.error = getString(R.string.err_msg_pass);
        }
        else if (phone.isEmpty()){
            signUpBinding.phoneTIL.isErrorEnabled = true;
            signUpBinding.phoneTIL.error = getString(R.string.err_msg_pass);
        }*/
    }

    private fun signUpWithFirebase() {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            //signup success
            progressDialog.dismiss()
            //get current user
            val firebaseUser = auth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(requireContext(),"Account created with email $email", Toast.LENGTH_SHORT).show()

            //open profile
            startActivity(Intent(requireContext(),MainActivity::class.java))
        }.addOnFailureListener{e ->
            progressDialog.dismiss()
            Toast.makeText(requireContext(),"SignUp failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


}