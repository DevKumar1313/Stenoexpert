package com.agamya.stenoexpert.dashboard

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.agamya.stenoexpert.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class Dashboard : AppCompatActivity() {
    private lateinit var bottomNav:BottomNavigationView
    private lateinit var slideNav:NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var drawer:DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bottomNav = findViewById(R.id.bottom)
        slideNav = findViewById(R.id.slideNav)
        toolbar = findViewById(R.id.toolbar)
        drawer = findViewById(R.id.drawer)

        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(this,drawer,R.string.OpenDrawer,R.string.CloseDrawer)
        drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

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

        slideNav.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.purchase -> Toast.makeText(this, "Purchase", Toast.LENGTH_SHORT).show()
            }

            drawer.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }


    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        }
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Toggle the drawer when the hamburger icon is clicked
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    //Function for Load Fragment on Frame Layout
    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.Frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}