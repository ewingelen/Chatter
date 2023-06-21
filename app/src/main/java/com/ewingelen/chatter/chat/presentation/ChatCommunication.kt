package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.chat.presentation.contract.ChatState
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.BaseEffectCommunication
import com.ewingelen.chatter.core.presentation.ObserveUi
import javax.inject.Inject

interface ChatCommunication : ObserveUi<ChatState, Unit> {

    fun showLoading()

    fun showData(chat: Chat)

    fun changeMessage(newMessage: String)

    fun changeUserPresenceInCall(presence: Boolean)

    fun showMessage(message: Message)

    fun removeMessage(message: Message)

    fun editMessage(newText: String, position: Int)

    class Base @Inject constructor() :
        ChatCommunication,
        BaseEffectCommunication<ChatState, Unit>(defaultState = ChatState()) {

        override fun showLoading() {
            updateState(state.value.copy(loading = true))
        }

        override fun showData(chat: Chat) {
            updateState(state.value.copy(chat = chat, loading = false))
        }

        override fun changeMessage(newMessage: String) {
            updateState(state.value.copy(enteredMessage = newMessage))
        }

        override fun changeUserPresenceInCall(presence: Boolean) {
            updateState(state.value.copy(userPresenceInCall = presence))
        }

        override fun showMessage(message: Message) {
            val messages = state.value.chat.messages.toMutableList()
            messages.add(message)
            updateState(
                state.value.copy(
                    chat = state.value.chat.copy(messages = messages),
                    enteredMessage = ""
                )
            )
            sendEffect(Unit)
        }

        override fun removeMessage(message: Message) {
            val messages = state.value.chat.messages.toMutableList()
            messages.remove(message)
            updateState(state.value.copy(chat = state.value.chat.copy(messages = messages)))
        }

        override fun editMessage(newText: String, position: Int) {
            val messages = state.value.chat.messages.toMutableList()
            messages[position] = messages.elementAt(position).copy(text = newText)
            updateState(state.value.copy(chat = state.value.chat.copy(messages = messages)))
        }
    }
}