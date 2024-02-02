package com.agamya.stenoexpert.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.agamya.stenoexpert.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    private lateinit var bottomNav:BottomNavigationView
    private lateinit var slideNav:NavigationView
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bottomNav = findViewById(R.id.bottom)
        slideNav = findViewById(R.id.slideNav)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        val toggle = 
        loadFragment(Home())


        bottomNav.setOnItemSelectedListener {
            when (it.itemId){
                R.id.home -> loadFragment(Home())
                R.id.course -> loadFragment(Course())
                R.id.download -> loadFragment(Download())
                R.id.helpDesk -> loadFragment(HelpDesk())
            }
            return@setOnItemSelectedListener true
        }


    }

    //Function for Load Fragment on Frame Layout
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}