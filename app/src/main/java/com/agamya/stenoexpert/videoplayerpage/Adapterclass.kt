package com.agamya.stenoexpert.videoplayerpage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class Adapterclass(val context: Context, val data:ArrayList<Arrayclass>) : RecyclerView.Adapter<Adapterclass.VH>() {
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image:ImageView=itemView.findViewById(R.id.image)
        val text1:TextView=itemView.findViewById(R.id.text1)
        val text2:TextView=itemView.findViewById(R.id.text2)
        val text3:TextView=itemView.findViewById(R.id.text3)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
     val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view,parent,false)
     return VH(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
     holder.image.setImageResource(data[position].image)
        holder.text1.text=data[position].text1
        holder.text2.text=data[position].text2
        holder.text3.text=data[position].text3

    }
}
