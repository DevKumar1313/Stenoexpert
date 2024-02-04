package com.agamya.stenoexpert.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import com.agamya.stenoexpert.R
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.agamya.stenoexpert.profile_page.ProfilePage

class SettingsPage : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var profile:ImageView
    private lateinit var autoPlay:SwitchCompat
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_page)
        profile = findViewById(R.id.profile_setting)
        autoPlay = findViewById(R.id.auto_play)
        toolbar = findViewById(R.id.setting_toolbar)
        Toast.makeText(this, "Design By Shiv", Toast.LENGTH_SHORT).show()
        setSupportActionBar(toolbar)


        toolbar.setNavigationOnClickListener {

            onBackPressed()
        }

        profile.setOnClickListener {
            val intent = Intent(this@SettingsPage,ProfilePage::class.java)
            startActivity(intent)
        }

        autoPlay.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Auto-Play ON", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Auto-Play OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }
}