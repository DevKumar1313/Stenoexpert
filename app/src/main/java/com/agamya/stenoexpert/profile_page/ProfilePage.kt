package com.agamya.stenoexpert.profile_page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.agamya.stenoexpert.R

class ProfilePage : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilepage)
        toolbar = findViewById(R.id.toolbar)
        Toast.makeText(this, "Design By Jyoti", Toast.LENGTH_SHORT).show()
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}