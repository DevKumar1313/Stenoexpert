package com.agamya.stenoexpert.dashboard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.agamya.stenoexpert.R
import com.agamya.stenoexpert.chatbot.BotResponse
import com.agamya.stenoexpert.chatbot.Constants.OPEN_GOOGLE
import com.agamya.stenoexpert.chatbot.Constants.OPEN_SEARCH
import com.agamya.stenoexpert.chatbot.Message
import com.agamya.stenoexpert.chatbot.MessageAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient


class HelpDesk : Fragment() {



    private val client = OkHttpClient()
    private val messageList = mutableListOf<Message>()
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var recyclerView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        recyclerView= view.findViewById(R.id.recyclerView)
        val messageInput: EditText = view.findViewById(R.id.messageInput)
        val sendButton: Button = view.findViewById(R.id.ask)

        recyclerView.layoutManager = LinearLayoutManager(view.context)


        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = Message(messageText, isSent = true)
                messageList.add(message)
                messageAdapter = MessageAdapter(messageList)
                recyclerView.adapter = messageAdapter
                recyclerView.scrollToPosition(messageList.size -1)

                // Add logic to send the message to the other user or process it
                // (e.g., through a chat API).
                messageInput.text.clear()
                botResponse(messageText)
            }

        }


        return view
    }

    private fun getResponse(question:String, callback:(String) ->Unit){

    }

    override fun onStart() {
        messageList.add(Message("Hey, How can i help you",false))
        messageAdapter = MessageAdapter(messageList)
        recyclerView.adapter = messageAdapter
        super.onStart()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun botResponse(message: String){
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main){
                val response = BotResponse.basicResponse(message)
                when(response){
                    OPEN_GOOGLE ->{
                        val site = Intent(Intent.ACTION_VIEW)
                        val url = "https://www.google.com/"
                        site.data = Uri.parse(url)
                        startActivity(site)
                    }
                    OPEN_SEARCH ->{
                        val site = Intent(Intent.ACTION_VIEW)
                        val search: String = message.substringAfter("search")
                        val url = "https://www.google.com/search?&q=$search"
                        site.data = Uri.parse(url)
                        startActivity(site)
                    }
                }
                messageList.add(Message(response,false))
                messageAdapter = MessageAdapter(messageList)
                recyclerView.adapter = messageAdapter
                recyclerView.scrollToPosition(messageList.size -1)
            }
        }


    }


}