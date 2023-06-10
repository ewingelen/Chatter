package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.auth.core.presentation.OnVerificationStateChanged
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.HandlePhoneNumberAction
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberAction
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberEffect
import com.ewingelen.chatter.auth.verifyPhone.presentation.contract.PhoneNumberState
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import com.ewingelen.chatter.core.presentation.ChangePhoneNumber
import com.ewingelen.chatter.core.presentation.NormalizePhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PhoneNumberViewModel @Inject constructor(
    private val verificationErrorMapper: VerificationErrorMapper,
    private val changePhoneNumber: ChangePhoneNumber,
    private val normalizePhoneNumber: NormalizePhoneNumber
) : BaseEffectViewModel<PhoneNumberState, PhoneNumberAction, PhoneNumberEffect>(
    defaultState = PhoneNumberState()
), OnVerificationStateChanged, HandlePhoneNumberAction {

    override fun handleAction(action: PhoneNumberAction) {
        action.handle(this)
    }

    override fun changePhoneNumber(newNumber: String) {
        val phoneNumber = changePhoneNumber.change(state.value.phoneNumber, newNumber)
        updateState(state.value.copy(phoneNumber = phoneNumber, errorVisible = false))
    }

    override fun verifyPhoneNumber() {
        updateState(state.value.copy(loading = true))
        val verifyPhoneNumber = VerifyPhoneNumber.Base(
            onVerificationStateChanged = this,
            normalizePhoneNumber.normalize(state.value.phoneNumber)
        )
        sendEffect(PhoneNumberEffect.VerificationStarted(verifyPhoneNumber))
    }

    override fun onVerificationCompleted() {
        sendEffect(PhoneNumberEffect.VerificationCompleted())
    }

    override fun onVerificationFailed(e: Exception) {
        val error = verificationErrorMapper.map(e)
        updateState(state.value.copy(loading = false, error = error, errorVisible = true))
    }

    override fun onCodeSent(verificationId: String) {
        updateState(state.value.copy(loading = false))
        sendEffect(PhoneNumberEffect.CodeSent(verificationId, state.value.phoneNumber))
    }
}