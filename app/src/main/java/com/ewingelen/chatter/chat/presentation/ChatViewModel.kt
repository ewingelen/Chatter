package com.ewingelen.chatter.chat.presentation

import com.ewingelen.chatter.chat.domain.ChatInteractor
import com.ewingelen.chatter.chat.presentation.model.ChatInfoUi
import com.ewingelen.chatter.chat.presentation.model.MessageUi
import com.ewingelen.chatter.core.presentation.BaseActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


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
        updateState(state.value.copy(enteredMessage = newMessage))
    }

    override fun sendMessage() {
        val messages = state.value.chatInfo.messages.toMutableList()
        messages.add(MessageUi(state.value.enteredMessage, true))
        updateState(
            ChatState(chatInfo = ChatInfoUi(messages = messages))
        )
        interactor.sendMessage()
    }
}