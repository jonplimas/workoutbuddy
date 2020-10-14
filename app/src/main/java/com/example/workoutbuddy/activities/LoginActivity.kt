package com.example.workoutbuddy.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.workoutbuddy.R
import com.example.workoutbuddy.UserLogin
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val LOGIN_REQUEST_CODE = 2
    private val SIGNUP_REQUEST_CODE = 3

    private val admin =
        UserLogin(1, "Admin", "password")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInputText: EditText = findViewById(R.id.usernameEditText)
        val passwordInputText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.login_button)
        // val signUpTextView: TextView = findViewById(R.id.signupTextView)

        loginButton.setOnClickListener {
            if (usernameInputText.text.toString() == admin.loginName && passwordInputText.text.toString() == admin.loginpassword) {
                loadingBar.isVisible = true
                val i = Intent(this, MainActivity::class.java)
                //add user info into extras to pass to main activity
                startActivity(i)
                finish()
            }
            // clear login input text
            usernameInputText.text.clear()
            passwordInputText.text.clear()
        }

        signupTextView.setOnClickListener {
            val i2 = Intent(this, SignupActivity::class.java)
            startActivityForResult(i2, SIGNUP_REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == SIGNUP_REQUEST_CODE) {
            // get data from returned extras
            // val eName = data?.getStringExtra(eName)
            // val ePassword = data?.getStringExtra(ePassword)
            // store values into a new loginCredentials
            // val newUser =
            //
        } else if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE) {
            Toast.makeText(this, "Come again soon!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        loadingBar.isVisible = false
    }

}