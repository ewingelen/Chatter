package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@HiltViewModel
class PhoneNumberViewModel @Inject constructor() :
    BaseEffectViewModel<PhoneNumberState, HandlePhoneNumberAction, HandlePhoneNumberEffect>(),
    OnVerificationStateChanged,
    HandlePhoneNumberAction {

    override fun defaultState() = PhoneNumberState()

    override fun handleAction(action: Action<HandlePhoneNumberAction>) {
        action.handle(this)
    }

    override fun changePhoneNumber(newNumber: String) {
        if (newNumber.length <= MAX_PHONE_NUMBER_LENGTH) {
            updateState(state.value.copy(phoneNumber = newNumber))
        }
    }

    override fun verifyPhoneNumber() {
        updateState(state.value.copy(isLoading = true))
        sendEffect(
            PhoneNumberEffect.VerificationStarted(
                VerifyPhoneNumber.Base(
                    phoneNumber = state.value.phoneNumber,
                    onVerificationStateChanged = this
                )
            )
        )
    }

    override fun onVerificationCompleted() {
        sendEffect(PhoneNumberEffect.VerificationCompleted())
    }

    override fun onVerificationFailed(e: Exception) {
        updateState(state.value.copy(errorText = e.localizedMessage, isLoading = false))
    }

    override fun onCodeSent(verificationId: String) {
        updateState(state.value.copy(isLoading = false))
        sendEffect(PhoneNumberEffect.CodeSent(verificationId))
    }

    private companion object {
        const val MAX_PHONE_NUMBER_LENGTH = 15
    }
}