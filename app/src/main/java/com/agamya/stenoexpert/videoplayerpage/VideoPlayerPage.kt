package com.agamya.stenoexpert.videoplayerpage

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R
import java.io.IOException


class VideoPlayerPage : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var videoView:VideoView
    private lateinit var mediaController: MediaController
    private lateinit var progressBar: ProgressBar
    private lateinit var toolbar:Toolbar
    private val url = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        rv=findViewById(R.id.recycle)
        videoView = findViewById(R.id.videoPlayer)
        progressBar = findViewById(R.id.progress)
        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        Toast.makeText(this, "Design By Neha", Toast.LENGTH_SHORT).show()

        try {
            mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)
            val uri = Uri.parse(url)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

        }catch (e: IOException){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
        }

    }
}