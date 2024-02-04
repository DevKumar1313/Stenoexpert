package com.agamya.stenoexpert.lecturepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class NotesAdapter(val data:ArrayList<NotesData>):RecyclerView.Adapter<NotesAdapter.VH>() {
    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val index:TextView = view.findViewById(R.id.index)
        val notesTitle:TextView = view.findViewById(R.id.notes_title)
        val notesSize:TextView = view.findViewById(R.id.notes_size)
        val notesDownload:ImageView = view.findViewById(R.id.notes_download)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notestemplate,parent,false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.index.text = (position+1).toString()
        holder.notesTitle.text = data[position].notes_title
        holder.notesSize.text = data[position].notes_size
    }
}