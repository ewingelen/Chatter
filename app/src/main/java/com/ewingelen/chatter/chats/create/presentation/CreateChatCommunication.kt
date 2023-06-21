package com.ewingelen.chatter.chats.create.presentation

import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatEffect
import com.ewingelen.chatter.chats.create.presentation.contract.CreateChatState
import com.ewingelen.chatter.core.presentation.BaseEffectCommunication
import com.ewingelen.chatter.core.presentation.ObserveUi
import javax.inject.Inject

interface CreateChatCommunication : ObserveUi<CreateChatState.Base, CreateChatEffect> {

    fun changePhoneNumber(newNumber: String)

    fun changeName(newName: String)

    fun changeSurname(newSurname: String)

    fun showEmptyNameError()

    fun showEmptyPhoneNumberError(error: String)

    fun showCreateChatError(error: String)

    fun chatCreated()

    class Base @Inject constructor() : CreateChatCommunication,
        BaseEffectCommunication<CreateChatState.Base, CreateChatEffect>(
            defaultState = CreateChatState.Base()
        ) {

        override fun changePhoneNumber(newNumber: String) {
            updateState(
                state.value.copy(
                    phoneNumber = newNumber,
                    phoneNumberErrorVisible = false
                )
            )
        }

        override fun changeName(newName: String) {
            updateState(state.value.copy(name = newName, emptyNameErrorVisible = false))
        }

        override fun changeSurname(newSurname: String) {
            updateState(state.value.copy(surname = newSurname))
        }

        override fun showEmptyNameError() {
            updateState(state.value.copy(emptyNameErrorVisible = true))
        }

        override fun showEmptyPhoneNumberError(error: String) {
            updateState(
                state.value.copy(
                    phoneNumberErrorVisible = true,
                    phoneNumberError = error
                )
            )
        }

        override fun showCreateChatError(error: String) {
            updateState(state.value.copy(createChatError = error, createChatErrorVisible = true))
        }

        override fun chatCreated() {
            sendEffect(CreateChatEffect.ChatCreated())
            updateState(
                state.value.copy(
                    createChatErrorVisible = false,
                    phoneNumber = "",
                    name = "",
                    surname = ""
                )
            )
        }
    }
}