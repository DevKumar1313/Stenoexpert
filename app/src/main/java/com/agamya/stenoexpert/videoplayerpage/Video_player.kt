package com.agamya.stenoexpert.videoplayerpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class Video_player : AppCompatActivity() {
     private lateinit var rv:RecyclerView
     private var data= ArrayList<Arrayclass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        rv=findViewById(R.id.recycle)

        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","1"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","2"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","3"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","4"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","5"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","6"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","7"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","8"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","9"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","10"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","11"))
        data.add(Arrayclass(R.drawable.download,"Lecture:Demo1","Video-25:45 mins","12"))


        rv.layoutManager=LinearLayoutManager(this)

        val adapter =Adapterclass(this,data)
        rv.adapter=adapter



    }
}