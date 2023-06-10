package com.ewingelen.chatter.chats.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.chats.domain.ChatsInteractor
import com.ewingelen.chatter.chats.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseNewViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val interactor: ChatsInteractor,
    private val communication: ChatsCommunication,
) : BaseNewViewModel<ChatsState, ChatsAction>(communication),
    HandleChatsAction {

    init {
        viewModelScope.launch {
            interactor.fetchTopBarTitle().collect { newTitle ->
                communication.changeTopBarTitle(newTitle)
            }
        }
    }

    override fun handleAction(action: ChatsAction) {
        action.handle(this)
    }
}

interface ChatsAction : Action<HandleChatsAction> {

}

interface HandleChatsAction {

    class OpenChat
}

interface InternetConnection