package com.ewingelen.chatter.chat.presentation.contract

import android.net.Uri
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.Action

interface ChatAction : Action<HandleChatAction> {

    class ChangeMessage(private val newMessage: String) : ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.changeMessage(newMessage)
        }
    }

    class SendMessage : ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.sendMessage()
        }
    }

    class AttachFiles(private val files: List<Uri>) : ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.sendFiles(files)
        }
    }

    class DeleteMessage(private val message: Message) : ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.deleteMessage(message)
        }
    }

    class EditMessage(private val newText: String, private val position: Int): ChatAction {

        override fun handle(handle: HandleChatAction) {
            handle.editMessage(newText, position)
        }
    }
}