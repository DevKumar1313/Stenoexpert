package com.agamya.stenoexpert.profile_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.internaldatabases.UserAuthDatabase

class ProfilePage : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var name:TextView
    private lateinit var email:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)
        toolbar = findViewById(R.id.toolbar)
        name = findViewById(R.id.username)
        email = findViewById(R.id.email)
        Toast.makeText(this, "Design By Jyoti", Toast.LENGTH_SHORT).show()
        setSupportActionBar(toolbar)
        getUserData()
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun getUserData(){
        // Example retrieval in your profile activity or fragment
        val dbHelper = UserAuthDatabase(this)
        val userData = dbHelper.getUserData()

        if (userData.moveToFirst()) {
            val nameColumnIndex = userData.getColumnIndex("name")
            val emailColumnIndex = userData.getColumnIndex("email")

            // Check if the column index is valid
            if (nameColumnIndex != -1 && emailColumnIndex != -1) {
                val userName = userData.getString(nameColumnIndex)
                val userEmail = userData.getString(emailColumnIndex)
                name.text = userName
                email.text = userEmail
            } else {
                // Handle the case when the column index is not valid
                // Log an error, show a default value, or take appropriate action
            }
        }

        userData.close() // Close the cursor when you're done with it
    }
}