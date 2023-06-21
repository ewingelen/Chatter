package com.ewingelen.chatter.chat.presentation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.chat.data.ProvideFileNameByUri
import com.ewingelen.chatter.chat.domain.ChatInteractor
import com.ewingelen.chatter.chat.presentation.contract.ChatAction
import com.ewingelen.chatter.chat.presentation.contract.ChatState
import com.ewingelen.chatter.chat.presentation.contract.HandleChatAction
import com.ewingelen.chatter.core.domain.model.AttachedFile
import com.ewingelen.chatter.core.domain.model.Message
import com.ewingelen.chatter.core.presentation.BaseNewEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val interactor: ChatInteractor,
    private val communication: ChatCommunication,
    savedStateHandle: SavedStateHandle,
    private val provideFileNameByUri: ProvideFileNameByUri
) : BaseNewEffectViewModel<ChatState, ChatAction, Unit>(communication), HandleChatAction {

    private val args = ChatNavigationArg(savedStateHandle)

    init {
        communication.showLoading()
        viewModelScope.launch {
            interactor.chat(id = args.chatId).collectLatest { chat ->
                communication.showData(chat)
            }
        }
        viewModelScope.launch {
            interactor.checkUserPresenceInCall().collectLatest { presence ->
                Timber.d(presence.toString())
                communication.changeUserPresenceInCall(presence)
            }
        }
    }

    override fun handleAction(action: ChatAction) {
        action.handle(this)
    }

    override fun changeMessage(newMessage: String) {
        communication.changeMessage(newMessage)
    }

    override fun sendMessage() {
        val message = Message(
            id = UUID.randomUUID().toString(),
            mine = true,
            text = stateValue().enteredMessage.trim()
        )
        viewModelScope.launch {
            interactor.sendMessage(args.chatId, message)
        }
        communication.showMessage(message)
    }

    override fun editMessage(newText: String, position: Int) {
        viewModelScope.launch {
            communication.editMessage(newText, position)
            interactor.editMessage(args.chatId, newText, position)
        }
    }

    override fun replyMessage(targetMessage: Message, answerMessage: Message) {
        TODO("Not yet implemented")
    }

    override fun deleteMessage(message: Message) {
        interactor.deleteMessage(args.chatId, message)
        communication.removeMessage(message)
    }

    override fun sendFiles(files: List<Uri>) {
        viewModelScope.launch {
            interactor.sendFiles(args.chatId, files)
        }
        files.forEach { file ->
            communication.showMessage(
                Message(
                    text = "",
                    mine = true,
                    file = AttachedFile(
                        downloadUrl = "",
                        size = "0.0",
                        type = provideFileNameByUri.provide(file).split(".").last()
                    )
                )
            )
        }
    }
}