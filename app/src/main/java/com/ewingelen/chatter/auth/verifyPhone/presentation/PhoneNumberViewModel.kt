package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.auth.core.presentation.OnVerificationStateChanged
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    private val verificationErrorMapper: VerificationErrorMapper
) : BaseEffectViewModel<PhoneNumberState, HandlePhoneNumberAction, HandlePhoneNumberEffect>(
    defaultState = PhoneNumberState()
), OnVerificationStateChanged, HandlePhoneNumberAction {

    override fun handleAction(action: Action<HandlePhoneNumberAction>) {
        action.handle(this)
    }

    override fun changePhoneNumber(newNumber: String) {
        if (newNumber.length <= MAX_PHONE_NUMBER_LENGTH) {
            updateState(state.value.copy(phoneNumber = newNumber))
        }
    }

    override fun verifyPhoneNumber() {
        updateState(state.value.copy(loading = true))
        sendEffect(
            PhoneNumberEffect.VerificationStarted(
                VerifyPhoneNumber.Base(state.value.phoneNumber, onVerificationStateChanged = this)
            )
        )
    }

    override fun onVerificationCompleted() {
        sendEffect(PhoneNumberEffect.VerificationCompleted())
    }

    override fun onVerificationFailed(e: Exception) {
        val error = verificationErrorMapper.map(e)
        updateState(state.value.copy(loading = false, error = error))
    }

    override fun onCodeSent(verificationId: String) {
        updateState(state.value.copy(loading = false))
        sendEffect(PhoneNumberEffect.CodeSent(verificationId, state.value.phoneNumber))
    }

    private companion object {
        const val MAX_PHONE_NUMBER_LENGTH = 15
    }
}