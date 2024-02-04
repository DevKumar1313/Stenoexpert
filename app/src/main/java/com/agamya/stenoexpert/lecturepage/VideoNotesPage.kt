package com.agamya.stenoexpert.lecturepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.agamya.stenoexpert.R
import com.google.android.material.tabs.TabLayout

class VideoNotesPage : AppCompatActivity() {
     private lateinit var tab:TabLayout
     private lateinit var View:ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_notes_page)

        val viewPager: ViewPager = findViewById(R.id.Viewpager)
        val tabLayout: TabLayout = findViewById(R.id.tab)

        val adapter = FragmentPagerAdapterNotes(supportFragmentManager)
        adapter.addFragment(VideoLecture(), "Video Lecture")
        adapter.addFragment(Notes(), "Notes")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}