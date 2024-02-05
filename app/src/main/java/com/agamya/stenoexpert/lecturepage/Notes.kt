package com.agamya.stenoexpert.lecturepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.dashboard.RecyclerViewClickInterface


class Notes : Fragment(),RecyclerViewClickInterface {

    private lateinit var rv:RecyclerView
    private lateinit var notesData:ArrayList<NotesData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        rv = view.findViewById(R.id.Rview2)
        rv.layoutManager = LinearLayoutManager(view.context)
        notesData = ArrayList()
        getData()

        val adapter = NotesAdapter(notesData,this)
        rv.adapter = adapter

        return view
    }


    private fun getData(){
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
        notesData.add(NotesData("Notes : Demo 1","Page - 16"))
    }

    override fun onClick(position: Int) {
        Toast.makeText(requireActivity(), "Available Soon", Toast.LENGTH_SHORT).show()
    }


}