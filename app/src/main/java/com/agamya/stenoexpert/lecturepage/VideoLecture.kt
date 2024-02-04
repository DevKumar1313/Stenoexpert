package com.agamya.stenoexpert.lecturepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R


class VideoLecture : Fragment() {
     private lateinit var rv:RecyclerView
     private lateinit var lectureData:ArrayList<LectureData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_video_lecture, container, false)
        rv = view.findViewById(R.id.Rview)
        rv.layoutManager = LinearLayoutManager(view.context)
        lectureData =ArrayList()
        getData()

        val adapter = VideoLectureAdapter(lectureData)
        rv.adapter=adapter


       return view
    }


    private fun getData(){
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
        lectureData.add(LectureData("Lecture : Demo 1","Video - 25:45 mins"))
    }

}