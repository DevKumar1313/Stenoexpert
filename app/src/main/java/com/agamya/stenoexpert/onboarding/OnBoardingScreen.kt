package com.agamya.stenoexpert.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.authentication.LoginPage

class OnBoardingScreen : AppCompatActivity() {
    private lateinit var onboardingItemAdapter: OnboardingItemAdapter
    private lateinit var indicatorsContainer:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
        setupIndicator()
        setCurrentIndicator(0)
    }
    private fun setOnboardingItems(){
        onboardingItemAdapter= OnboardingItemAdapter(
            listOf(
                OnboardingItemClass(R.drawable.nk,"What a Scene!!","Wonderfull!! what a great view"),
                OnboardingItemClass(R.drawable.baseline_star_rate_24,"What a Scene!!","Wonderfull!! what a great view"),
                 OnboardingItemClass(R.drawable.images2345,"What a Scene!!","Wonderfull!! what a great view"),

        )
        )
        val onboardingViewPager=findViewById<ViewPager2>(R.id.onboarding_viewpager)
        onboardingViewPager.adapter=onboardingItemAdapter
        onboardingViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0)as RecyclerView).overScrollMode=
        RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.imagenext).setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemAdapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigationToHomeActivity()
            }
        }
            findViewById<TextView>(R.id.textskip).setOnClickListener {
                navigationToHomeActivity()
            }
        
        findViewById<Button>(R.id.get_startbtn).setOnClickListener {
            navigationToHomeActivity()
        }
    }
    private fun navigationToHomeActivity(){
        startActivity(Intent(applicationContext,LoginPage::class.java))
        finish()
    }
    private fun setupIndicator(){
        indicatorsContainer=findViewById(R.id.indicatorscontainer)
        val indicators= arrayOfNulls<ImageView>(onboardingItemAdapter.itemCount)
        val layoutParams:LinearLayout.LayoutParams=
        LinearLayout.LayoutParams(WRAP_CONTENT,WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i]= ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams=layoutParams
                indicatorsContainer.addView(it)
            }
            }
        }


    private fun setCurrentIndicator(position:Int){
        val childCount=indicatorsContainer.childCount
        for (i in 0 until  childCount){
            val imageView=indicatorsContainer.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            }else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }

        }
    }
}