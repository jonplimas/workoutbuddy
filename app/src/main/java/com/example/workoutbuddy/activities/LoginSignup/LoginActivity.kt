package com.example.workoutbuddy.activities.LoginSignup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.Data.Login
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.LoginViewModel
import com.example.workoutbuddy.activities.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


private lateinit var loginViewModel: LoginViewModel
private lateinit var mLoginList: MutableList<Login>

class LoginActivity : AppCompatActivity() {

    private val LOGIN_REQUEST_CODE = 2
    private val SIGNUP_REQUEST_CODE = 3

    private val admin = Login("Admin", "password")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInputText: EditText = findViewById(R.id.usernameEditText)
        val passwordInputText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.login_button)
        val signUpTextView: TextView = findViewById(R.id.signupTextView)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.allLogins.observe(this, Observer { logins ->
            logins?.let { mLoginList = it as MutableList<Login> }
        })


        loginButton.setOnClickListener {

            //Check for login credentials exist
            var loginIsValid = false
            for (i in 0..mLoginList.size-1) {
                if (usernameInputText.text.toString() == mLoginList[i].loginName && passwordInputText.text.toString() == mLoginList[i].loginPassword) {
                    loginIsValid = true
                    break
                }
            }

            if (loginIsValid) {
                loadingBar.isVisible = true
                val i = Intent(this, MainActivity::class.java)
                //add user info into extras to pass to main activity
                i.putExtra("userName", usernameInputText.text.toString())

                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, "FAILED LOGIN: Invalid Credentials", Toast.LENGTH_SHORT).show()
            }
            // clear login input text
            usernameInputText.text.clear()
            passwordInputText.text.clear()
        }



        signUpTextView.setOnClickListener {
            val i2 = Intent(this, SignupActivity::class.java)
            startActivityForResult(i2, SIGNUP_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SIGNUP_REQUEST_CODE) {
            // get data from returned extras
            val name = data?.getStringExtra("login").toString()
            val password = data?.getStringExtra("password").toString()

            // store values into a new loginCredentials
            val newLogin = Login(name, password)
            // update DB with new login
            mLoginList.add(newLogin)
            loginViewModel.insertLogin(newLogin)

            Toast.makeText(this, "SUCCESSFULLY CREATED NEW USER--> Name: ${mLoginList[mLoginList.size-1].loginName},  Password:${mLoginList[mLoginList.size-1].loginPassword}", Toast.LENGTH_SHORT).show()
        } else if (requestCode == SIGNUP_REQUEST_CODE && resultCode != Activity.RESULT_OK) {
            Toast.makeText(this, "FAILED TO CREATE NEW USER ", Toast.LENGTH_SHORT).show()

        } else if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE) {
            Toast.makeText(this, "Come again soon!", Toast.LENGTH_LONG).show()
        }
    }


    override fun onResume() {
        super.onResume()
        loadingBar.isVisible = false
    }

}