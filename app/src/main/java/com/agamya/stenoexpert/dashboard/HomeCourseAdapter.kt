package com.agamya.stenoexpert.dashboard

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class HomeCourseAdapter(val context: Context, private val dataList: ArrayList<String>): RecyclerView.Adapter<HomeCourseAdapter.VH>() {
    class VH(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val courseTitle:TextView = itemView.findViewById(R.id.course_Title)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
                val screenWidth = getScreenWidth()
        val visibleFraction = 0.75 // Adjust the visible fraction as needed

        val itemWidth = (screenWidth * visibleFraction).toInt()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_course_model, parent, false)
        view.layoutParams.width = itemWidth
        return VH(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.courseTitle.text = dataList[position]
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        context.display?.getRealMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}