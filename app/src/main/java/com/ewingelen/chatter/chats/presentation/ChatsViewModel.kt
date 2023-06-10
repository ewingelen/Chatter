package com.ewingelen.chatter.chats.presentation

import com.ewingelen.chatter.chats.domain.ChatsInteractor
import com.ewingelen.chatter.chats.presentation.contract.ChatsState
import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    interactor: ChatsInteractor
) : BaseActionViewModel<ChatsState, ChatsAction>(defaultState = ChatsState()),
    HandleChatsAction {

    init {
    }

    override fun handleAction(action: ChatsAction) {
        action.handle(this)
    }
}

interface ChatsAction : Action<HandleChatsAction> {

}

interface HandleChatsAction {

    class OpenChat

    class AddChat
}