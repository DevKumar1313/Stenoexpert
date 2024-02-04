package com.agamya.stenoexpert.dashboard

import android.os.Bundle
import android.content.Intent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.lecturepage.VideoNotesPage


class Course : Fragment(),RecyclerViewClickInterface {

    private lateinit var rv: RecyclerView

    private lateinit var data:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_course, container, false)

        rv = view.findViewById(R.id.rv)

        rv.layoutManager = GridLayoutManager(view.context,2)

        data = ArrayList()
        data.add("Hello")
        data.add("Working")
        data.add("Yeah")
        data.add("Ohho")
        val adapter = CourseAdapter(view.context,data,this)
        rv.adapter  = adapter
        return view
    }

    override fun onClick(position: Int) {
        val intent = Intent(requireActivity(),VideoNotesPage::class.java)
        startActivity(intent)
    }

}