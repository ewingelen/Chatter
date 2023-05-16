package com.ewingelen.chatter.createChat.presentation

import com.ewingelen.chatter.core.presentation.BaseActionViewModel
import com.ewingelen.chatter.createChat.domain.CreateChatInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
@HiltViewModel
class CreateChatViewModel @Inject constructor(
    private val interactor: CreateChatInteractor
) : BaseActionViewModel<CreateChatState, CreateChatAction>(CreateChatState()),
    HandleCreateChatAction {

    override fun handleAction(action: CreateChatAction) {
        action.handle(this)
    }

    override fun changeName(newName: String) {
        updateState(state.value.copy(name = newName))
    }

    override fun changePhoneNumber(newNumber: String) {
        updateState(state.value.copy(phoneNumber = newNumber))
    }

    override fun createChat() {
        interactor.createChat()
    }
}