package com.ewingelen.chatter.chat.presentation.contract

import android.net.Uri
import com.ewingelen.chatter.core.domain.model.Message

interface HandleChatAction {

    fun changeMessage(newMessage: String)

    fun sendMessage()

    fun deleteMessage(message: Message)

    fun editMessage(newText: String, position: Int)

    fun replyMessage(targetMessage: Message, answerMessage: Message)

    fun sendFiles(files: List<Uri>)
}