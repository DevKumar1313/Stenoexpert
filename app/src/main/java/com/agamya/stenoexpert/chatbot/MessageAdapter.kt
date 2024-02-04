package com.agamya.stenoexpert.chatbot

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R

class MessageAdapter(private val messages: List<Message>):RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView = itemView.findViewById(R.id.messageText)
        val messageContainer: LinearLayout = itemView.findViewById(R.id.messageContainer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text
        if (message.isSent){
            holder.messageText.setBackgroundResource(R.drawable.chat_style)
        }else{
            holder.messageText.setBackgroundResource(R.drawable.chat_style2)
        }

        // Set gravity based on whether the message is sent or received
        val gravity = if (message.isSent) Gravity.START else Gravity.END
        holder.messageContainer.gravity = gravity
    }

}