package com.agamya.stenoexpert.chatbot

import com.agamya.stenoexpert.chatbot.Constants.OPEN_GOOGLE
import com.agamya.stenoexpert.chatbot.Constants.OPEN_SEARCH

object BotResponse {
    fun basicResponse(message:String):String{
        val random = (0..2).random()
        val messages = message.lowercase()

        return when{
            messages.contains("hello") ->{
                when(random) {
                    0->"Hello There!"
                    1->"How can i help you"
                    2->"Hey"
                    else ->"Error"
                }
            }

            messages.contains("time") && message.contains("?")->{
                Time.timeStamp()
            }

            messages.contains("open") && message.contains("google")->{
                OPEN_GOOGLE
            }

            messages.contains("search") ->{
                OPEN_SEARCH
            }

            messages.contains("how are you") ->{
                when(random) {
                    0->"I m fine, thanks for asking"
                    1->"Pretty good,  What about you?"
                    2->"It's good time"
                    else ->"Error"
                }
            }
            else->{
                when(random) {
                    0->"I don't understand..."
                    1->"Sorry, i don't understand"
                    2->"Try asking me something different!"
                    else ->"Error"
                }
            }
        }
    }
}