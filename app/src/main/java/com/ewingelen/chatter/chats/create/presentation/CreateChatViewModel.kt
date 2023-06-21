package com.ewingelen.chatter.chats.create.presentation

import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.create.domain.CreateChatInteractor
import com.ewingelen.chatter.chats.create.domain.CreateChatResult
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatAction
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatState
import com.ewingelen.chatter.chats.create.presentation.contract.HandleCreateChatAction
import com.ewingelen.chatter.chats.main.presentation.ChatsCommunication
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.model.Chat
import com.ewingelen.chatter.core.presentation.BaseNewEffectViewModel
import com.ewingelen.chatter.core.presentation.ChangePhoneNumber
import com.ewingelen.chatter.core.presentation.NormalizePhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class CreateChatViewModel @Inject constructor(
    private val interactor: CreateChatInteractor,
    private val communication: CreateChatCommunication,
    private val createChatResultMapper: CreateChatResult.Mapper,
    private val ioDispatcher: CoroutineDispatcher,
    private val provideResources: ProvideResources,
    private val changePhoneNumber: ChangePhoneNumber,
    private val normalizePhoneNumber: NormalizePhoneNumber,
    private val chatsCommunication: ChatsCommunication
) : BaseNewEffectViewModel<CreateChatState.Base, CreateChatAction, CreateChatEffect>(communication),
    HandleCreateChatAction {

    override fun handleAction(action: CreateChatAction) {
        action.handle(this)
    }

    override fun changeName(newName: String) {
        communication.changeName(newName)
    }

    override fun changeSurname(newSurname: String) {
        communication.changeSurname(newSurname)
    }

    override fun changePhoneNumber(newNumber: String) {
        val phoneNumber = changePhoneNumber.change(stateValue().phoneNumber, newNumber)
        communication.changePhoneNumber(phoneNumber)
    }

    override fun createChat() {
        if (stateValue().name.isEmpty()) {
            communication.showEmptyNameError()
        }

        if (stateValue().phoneNumber.isEmpty()) {
            val errorEmptyPhoneNumber = provideResources.string(R.string.error_empty_contact_number)
            communication.showEmptyPhoneNumberError(errorEmptyPhoneNumber)
        }

        if (stateValue().name.isNotEmpty() && stateValue().phoneNumber.isNotEmpty()) {
            viewModelScope.launch(ioDispatcher) {
                val chat = Chat(
                    id = UUID.randomUUID().toString(),
                    contactName = stateValue().name,
                    contactSurname = stateValue().surname,
                    contactPhoneNumber = normalizePhoneNumber.normalize(state().value.phoneNumber)
                )
                interactor.createChat(chat).map(createChatResultMapper)
                chatsCommunication.createChat(chat)
            }
        }
    }
}