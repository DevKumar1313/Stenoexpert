package com.agamya.stenoexpert.lecturepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class VideoLectureAdapter(val data:ArrayList<LectureData>):RecyclerView.Adapter<VideoLectureAdapter.VH>() {
    class VH(view2: View) : RecyclerView.ViewHolder(view2) {
        val lectureIndex:TextView = view2.findViewById(R.id.lecture_index)
        val lectureTitle:TextView = view2.findViewById(R.id.lecture_title)
        val lectureDuration:TextView =view2.findViewById(R.id.lecture_duration)
        val lectureDownload:ImageView = view2.findViewById(R.id.lecture_download)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view2 = LayoutInflater.from(parent.context).inflate(R.layout.videotemplate,parent,false)
        return VH(view2)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
       holder.lectureIndex.text = (position+1).toString()
        holder.lectureTitle.text = data[position].lectureTitle
        holder.lectureDuration.text = data[position].lectureDuration
    }
}