package com.ewingelen.chatter.chats.presentation

import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseActionViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 14.05.2023.
 */
@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val interactor: ChatsInteractor
) : BaseActionViewModel<ChatsState, HandleChatsAction>(defaultState = ChatsState()),
    HandleChatsAction {

    init {
        updateState(state.value.copy(chats = interactor.fetchChats()))
    }

    override fun handleAction(action: Action<HandleChatsAction>) {
        action.handle(this)
    }
}

interface ChatsAction : Action<HandleChatsAction> {

}

interface HandleChatsAction {

    class OpenChat

    class AddChat
}