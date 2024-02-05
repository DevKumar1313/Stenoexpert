package com.agamya.stenoexpert.lecturepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.dashboard.RecyclerViewClickInterface

class VideoLectureAdapter(private val data:ArrayList<LectureData>, private val click:RecyclerViewClickInterface):RecyclerView.Adapter<VideoLectureAdapter.VH>() {
    class VH(view2: View) : RecyclerView.ViewHolder(view2) {
        val lectureIndex:TextView = view2.findViewById(R.id.lecture_index)
        val lectureTitle:TextView = view2.findViewById(R.id.lecture_title)
        val lectureDuration:TextView =view2.findViewById(R.id.lecture_duration)
        val lectureDownload:ImageView = view2.findViewById(R.id.lecture_download)
        val layout:RelativeLayout = view2.findViewById(R.id.layout)

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
        holder.layout.setOnClickListener {
            click.onClick(holder.adapterPosition)
        }
    }
}