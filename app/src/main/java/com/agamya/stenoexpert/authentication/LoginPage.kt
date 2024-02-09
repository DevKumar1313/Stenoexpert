package com.agamya.stenoexpert.authentication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.dashboard.Dashboard
import com.agamya.stenoexpert.internaldatabases.UserAuth
import com.agamya.stenoexpert.internaldatabases.UserAuthDatabase
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

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
                authUser(userEmail.text.toString(),password.text.toString())
            }
        }
    }

    private fun authUser(email: String, password: String) {
        val url = "http://154.41.254.57:7000/loginUser"
        val queue = Volley.newRequestQueue(this)
        // Create the request object with POST method and the data to be sent
        val postData = JSONObject()
        postData.put("email", email)
        postData.put("password", password)// Add your data fields here

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, postData,
            { response ->
                // Handle the JSON response

                try {
                    val status = response.getString("status")
                    if (status == "Success") {
                        // Successful login, handle the data in response.getJSONObject("data")
                        val userData = response.getJSONObject("data")
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

                        // Assuming you have the user data in a variable named userData
                        val dbHelper = UserAuthDatabase(this)
                        val user = UserAuth(0, userData.getString("name"), userData.getString("email"), userData.getString("password"))
                        dbHelper.addUser(user) //Add User in Database

                        //This code block store status of user Login or not
                        val pref2 = getSharedPreferences("LoginStatus",Context.MODE_PRIVATE)
                        val editor = pref2.edit()
                        editor.putBoolean("status",true)
                        editor.apply()

                        //This code block navigate this page to Dashboard
                        val intent = Intent(this@LoginPage, Dashboard::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // Failed login, handle the error message in response.getString("message")
                        val errorMessage = response.getString("message")
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Check Your Network", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
                if (error.networkResponse?.statusCode == 401) {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }else if (error.networkResponse?.statusCode == 500){
                    Toast.makeText(this, "Internal server error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Network error", Toast.LENGTH_SHORT).show()
                }
            }
        )

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}
