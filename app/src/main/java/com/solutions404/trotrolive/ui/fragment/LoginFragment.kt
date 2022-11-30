package com.solutions404.trotrolive.ui.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.solutions404.trotrolive.R
import com.solutions404.trotrolive.databinding.FragmentLoginBinding
import com.solutions404.trotrolive.ui.activities.MainActivity


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
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false)


        loginBn = loginBinding.loginBn
        emailET = loginBinding.emailET
        passwordET = loginBinding.passwordET

        progressDialog = ProgressDialog(requireActivity())
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Logging in...")
        progressDialog.setCanceledOnTouchOutside(false)

        loginBinding.signUpPromptTV.setOnClickListener{
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            )
        }

        loginBinding.loginBn.setOnClickListener {
            validateData()
        }

        loginBinding.skipLoginTV.setOnClickListener{
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }

        return loginBinding.root

    }

    private fun validateData() {
        email = emailET.text.toString().trim()
        password = passwordET.text.toString().trim()

        if (email.isEmpty()){
            loginBinding.emailTIL.isErrorEnabled = true;
            loginBinding.emailTIL.error = getString(R.string.err_msg_email);
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            loginBinding.emailTIL.isErrorEnabled = true;
            loginBinding.emailTIL.error = getString(R.string.err_msg_invalid_email);
        }
        else if (password.isEmpty()){
            loginBinding.passwordTIL.isErrorEnabled = true;
            loginBinding.passwordTIL.error = getString(R.string.err_msg_pass);
        }
        else{
            progressDialog.show()
            loginWithFirebase()
        }
    }

    private fun loginWithFirebase() {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            progressDialog.dismiss()

            val firebaseUser = auth.currentUser
            Toast.makeText(requireContext(), "Logged in as ${firebaseUser!!.email}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(requireContext(), MainActivity::class.java))

        }.addOnFailureListener { e ->
            progressDialog.dismiss()
            Toast.makeText(requireContext(), "Login failed due to: ${e.message}", Toast.LENGTH_LONG).show()
        }    }


    private fun checkUser() {
        //if user is already logged in, go to Dashboard
        //get current user
        val firebaseUser = auth.currentUser
        if(firebaseUser != null){
            //user is already logged in
            startActivity(Intent(requireContext(), MainActivity::class.java))
        }    }

}