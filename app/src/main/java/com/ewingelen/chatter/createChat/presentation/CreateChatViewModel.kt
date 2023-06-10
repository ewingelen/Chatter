package com.ewingelen.chatter.createChat.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import com.ewingelen.chatter.core.presentation.ChangePhoneNumber
import com.ewingelen.chatter.core.presentation.NormalizePhoneNumber
import com.ewingelen.chatter.createChat.domain.CreateChatInteractor
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatAction
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.createChat.presentation.contract.CreateChatState
import com.ewingelen.chatter.createChat.presentation.contract.HandleCreateChatAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(
    private val interactor: CreateChatInteractor,
    private val ioDispatcher: CoroutineDispatcher,
    private val provideResources: ProvideResources,
    private val changePhoneNumber: ChangePhoneNumber,
    private val normalizePhoneNumber: NormalizePhoneNumber
) : BaseEffectViewModel<CreateChatState, CreateChatAction, CreateChatEffect>(CreateChatState()),
    HandleCreateChatAction {

    override fun handleAction(action: CreateChatAction) {
        action.handle(this)
    }

    override fun changeName(newName: String) {
        updateState(state.value.copy(name = newName))
        if (state.value.errorEmptyNameShowing) {
            updateState(state.value.copy(errorEmptyNameShowing = false, errorEmptyName = ""))
        }
    }

    override fun changePhoneNumber(newNumber: String) {
        val phoneNumber = changePhoneNumber.change(state.value.phoneNumber, newNumber)
        updateState(state.value.copy(phoneNumber = phoneNumber, errorVisible = false))
        if (state.value.errorEmptyPhoneNumberShowing) {
            updateState(
                state.value.copy(
                    errorEmptyPhoneNumberShowing = false,
                    errorEmptyPhoneNumber = ""
                )
            )
        }
    }

    override fun createChat() {
        if (state.value.name.isEmpty()) {
            updateState(
                state.value.copy(
                    errorEmptyNameShowing = true,
                    errorEmptyName = provideResources.string(R.string.error_empty_contact_name)
                )
            )
        }

        if (state.value.phoneNumber.isEmpty()) {
            updateState(
                state.value.copy(
                    errorEmptyPhoneNumberShowing = true,
                    errorEmptyPhoneNumber = provideResources.string(R.string.error_empty_contact_number),
                )
            )
        }

        if (state.value.name.isNotEmpty() && state.value.phoneNumber.isNotEmpty()) {
            viewModelScope.launch(ioDispatcher) {
                interactor.createChat(
                    chat = Chat(
                        contactName = state.value.name,
                        contactPhoneNumber = normalizePhoneNumber.normalize(state.value.phoneNumber)
                    ),
                    onSuccess = { sendEffect(CreateChatEffect.ChatCreated()) },
                    onFail = { updateState(state.value.copy(error = it, errorVisible = true)) }
                )
            }
        }
    }
}