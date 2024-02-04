package com.agamya.stenoexpert.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R


class Home : Fragment() {

    private lateinit var rv:RecyclerView
    private lateinit var rv2:RecyclerView
    private lateinit var data:ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rv = view.findViewById(R.id.rv)
        rv2 = view.findViewById(R.id.rv2)
        rv.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        rv2.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.HORIZONTAL,false)
        data = ArrayList()
        data.add("Hello")
        data.add("Working")
        data.add("Yeah")
        data.add("Ohho")
        val adapter = HomeCourseAdapter(view.context,data)
        rv.adapter  = adapter
        rv2.adapter  = adapter

        return view
    }


}