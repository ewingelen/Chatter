package com.ewingelen.chatter.chats.presentation

import com.ewingelen.chatter.chats.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.BaseCommunication
import com.ewingelen.chatter.core.presentation.ObserveState
import javax.inject.Inject

interface ChatsCommunication : ObserveState<ChatsState> {

    fun changeTopBarTitle(newTitle: String)

    class Base @Inject constructor() : BaseCommunication<ChatsState>(ChatsState()),
        ChatsCommunication {

        override fun changeTopBarTitle(newTitle: String) {
            updateState(state.value.copy(topBarTitle = newTitle))
        }
    }
}