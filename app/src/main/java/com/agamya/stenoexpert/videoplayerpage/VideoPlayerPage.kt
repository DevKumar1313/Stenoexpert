package com.agamya.stenoexpert.videoplayerpage

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R


class VideoPlayerPage : AppCompatActivity() {
    private lateinit var rv:RecyclerView
    private lateinit var videoView:VideoView
    private lateinit var mediaController: MediaController
    private lateinit var progressBar: ProgressBar
    private lateinit var toolbar:Toolbar
    private val url = "http://154.41.254.57:3000"
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

        // Execute the AsyncTask
        NetworkTask().execute()

    }


    @SuppressLint("StaticFieldLeak")
    private inner class NetworkTask : AsyncTask<Void?, Void?, Void?>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Void?): Void? {
            // Your network-related code here
            try {
                // Simulate a delay
                Thread.sleep(2000)
                // Continue with the rest of your network-related code
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            // After the network operation is complete, start playing the video
            playVideo()
            progressBar.visibility = View.GONE
        }
    }
    private fun playVideo() {
        // Replace "your_video_url" with the actual URL of your video val videoUrl = "your_video_url"
        videoView.setVideoPath(url)
        // Create media controller
        mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)
        // Start playing the video
        videoView.start()
    }


}

