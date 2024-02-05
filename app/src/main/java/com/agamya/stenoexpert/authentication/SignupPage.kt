package com.agamya.stenoexpert.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.agamya.stenoexpert.R
import android.content.Intent
import android.widget.Toast

class SignupPage : AppCompatActivity() {
    private lateinit var userName:EditText
    private lateinit var userEmail:EditText
    private lateinit var password:EditText
    private lateinit var signUpBtn:Button
    private lateinit var signInBtn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)
        userName = findViewById(R.id.name)
        userEmail = findViewById(R.id.email_id)
        password = findViewById(R.id.password)
        signUpBtn = findViewById(R.id.sign_up)
        signInBtn = findViewById(R.id.sign_in)

        signUpBtn.setOnClickListener {
            if (userName.text.isEmpty()){
                userName.error = "This field required"
            }

            if (userEmail.text.isEmpty()){
                userEmail.error = "This field required"
            }

            if (password.text.isEmpty()){
                password.error = "This field required"
            }

            if (userName.text.isNotEmpty()&&userEmail.text.isNotEmpty()&&password.text.isNotEmpty()){
                //Logic for User Registration
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignupPage,LoginPage::class.java)
                startActivity(intent)
                finish()
            }

            signInBtn.setOnClickListener {
                val intent = Intent(this@SignupPage,LoginPage::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}