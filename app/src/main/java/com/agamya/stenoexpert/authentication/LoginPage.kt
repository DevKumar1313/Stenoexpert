package com.agamya.stenoexpert.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.dashboard.Dashboard

class LoginPage : AppCompatActivity() {
    private lateinit var userEmail:EditText
    private lateinit var password:EditText
    private lateinit var signInBtn:Button
    private lateinit var signUpBtn:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        userEmail = findViewById(R.id.email_id)
        password = findViewById(R.id.password)
        signUpBtn = findViewById(R.id.sign_up)
        signInBtn = findViewById(R.id.sign_in)


        val pref = getSharedPreferences("LoginStatus",Context.MODE_PRIVATE)
        val status = pref.getBoolean("status",false)

        if (status){
            val intent = Intent(this@LoginPage, Dashboard::class.java)
            startActivity(intent)
            finish()
        }


        signUpBtn.setOnClickListener {
                val intent = Intent(this@LoginPage,SignupPage::class.java)
                startActivity(intent)
                finish()
        }

        signInBtn.setOnClickListener {
                if (userEmail.text.isEmpty()){
                    userEmail.error = "This field required"
                }

                if (password.text.isEmpty()){
                    password.error = "This field required"
                }
            if (userEmail.text.isNotEmpty()&&password.text.isNotEmpty()) {
                val pref2 = getSharedPreferences("LoginStatus",Context.MODE_PRIVATE)
                val editor = pref2.edit()
                editor.putBoolean("status",true)
                editor.apply()
                val intent = Intent(this@LoginPage, Dashboard::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
