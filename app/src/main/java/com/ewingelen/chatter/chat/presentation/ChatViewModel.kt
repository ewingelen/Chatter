package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.core.presentation.BaseActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val interactor: ChatInteractor,
) : BaseActionViewModel<ChatState, ChatAction>(ChatState()), HandleChatAction {

    init {
        val chatInfo = interactor.chatInfo(contactId = "")
        updateState(state.value.copy(chatInfo = chatInfo))
    }

    override fun handleAction(action: ChatAction) {
        action.handle(this)
    }

    override fun changeMessage(newMessage: String) {
        updateState(ChatState(enteredMessage = newMessage))
    }

    override fun sendMessage() {
        interactor.sendMessage()
    }
}