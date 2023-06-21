package com.ewingelen.chatter.chats.main.presentation

import com.ewingelen.chatter.chats.main.presentation.contract.ChatsState
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.BaseCommunication
import com.ewingelen.chatter.core.presentation.ObserveState
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
interface ChatsCommunication : ObserveState<ChatsState> {

    fun changeTopBarTitle(newTitle: String)

    fun showChats(chats: List<Chat>)

    fun deleteChat(chat: Chat)

    fun createChat(chat: Chat)

    class Base @Inject constructor() : BaseCommunication<ChatsState>(ChatsState()),
        ChatsCommunication {

        override fun changeTopBarTitle(newTitle: String) {
            updateState(state.value.copy(topBarTitle = newTitle))
        }

        override fun showChats(chats: List<Chat>) {
            updateState(state.value.copy(chats = chats, loading = false))
        }

        override fun deleteChat(chat: Chat) {
            val chats = state.value.chats.toMutableList()
            chats.remove(chat)
            updateState(state.value.copy(chats = chats))
        }

        override fun createChat(chat: Chat) {
            val chats = state.value.chats.toMutableList()
            chats.add(chat)
            updateState(state.value.copy(chats = chats))
        }
    }
}