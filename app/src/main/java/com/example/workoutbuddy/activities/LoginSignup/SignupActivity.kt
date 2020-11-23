package com.example.workoutbuddy.activities.LoginSignup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.workoutbuddy.Data.User
import com.example.workoutbuddy.R
import com.example.workoutbuddy.ViewModels.UserViewModel
import kotlinx.android.synthetic.main.fragment_user.*


private lateinit var userViewModel: UserViewModel
private lateinit var mUserList: List<User>

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val mStartActBtn = findViewById<Button>(R.id.startActBtn)
        val submitButton = findViewById<Button>(R.id.submitNewUserButton)

        val signupName: EditText = findViewById(R.id.signupUsernameEditText)
        val signupPass: EditText = findViewById(R.id.signupPasswordEditText)
        val signupPass2: EditText = findViewById(R.id.signupPasswordEditText2)


        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer { users ->
            users.let { mUserList = it }
        })



        submitButton.setOnClickListener {
            //input validation
            if( TextUtils.isEmpty(signupName.text) && TextUtils.isEmpty(signupPass.text) && signupPass.text.toString() == signupPass2.text.toString()) {
                Toast.makeText(this, "UNSUCCESSFUL SUBMISSION, SOMETHING EMPTY", Toast.LENGTH_SHORT).show()
            } else {
                // flag to check for duplicates
                var noDuplicates = true
                // iterate through list of usernames to see if username exists
                for(i in 0..mUserList.size-1) {
                    if(signupName.text.toString() == mUserList[i].userName) {
                        Toast.makeText(this, "UNSUCCESSFUL SUBMISSION, USERNAME ALREADY EXISTS", Toast.LENGTH_SHORT).show()
                        noDuplicates = false
                        break
                    }
                }

                if(noDuplicates) {
                    val name = signupName.text.toString()
                    val password = signupPass.text.toString()
                    // insert new user int view model
                    val newUser = User(name)
                    userViewModel.insertUser(newUser)

                    // use reply intent to send new Login information
                    val i = Intent()
                    i.putExtra("login", name)
                    i.putExtra("password", password)
                    Toast.makeText(this, "SENDING-->  Name: $name,  Password: $password", Toast.LENGTH_LONG).show()

                    setResult(Activity.RESULT_OK, i)
                    finish()
                }
            }
        }



        // handle back button click
        mStartActBtn.setOnClickListener {
            // close out activity
            finish()
        }
    }
}