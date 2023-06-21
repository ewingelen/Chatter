package com.ewingelen.chatter.chats.main.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.chats.main.domain.ChatsInteractor
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsAction
import com.ewingelen.chatter.chats.main.presentation.contract.ChatsState
import com.ewingelen.chatter.chats.main.presentation.contract.HandleChatsAction
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.BaseNewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val interactor: ChatsInteractor,
    private val communication: ChatsCommunication,
) : BaseNewViewModel<ChatsState, ChatsAction>(communication),
    HandleChatsAction {

    init {
//        OneSignal.promptForPushNotifications()
        viewModelScope.launch {
            interactor.fetchTopBarTitle().collectLatest { newTitle ->
                communication.changeTopBarTitle(newTitle)
            }
        }
        viewModelScope.launch {
            interactor.fetchChats().collectLatest { chats ->
                communication.showChats(chats)
            }
        }
    }

    override fun handleAction(action: ChatsAction) {
        action.handle(this)
    }

    override fun deleteChat(chat: Chat) {
        interactor.deleteChat(chat.id)
        communication.deleteChat(chat)
    }
}