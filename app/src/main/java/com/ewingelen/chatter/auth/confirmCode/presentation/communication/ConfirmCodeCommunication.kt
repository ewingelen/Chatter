package com.ewingelen.chatter.auth.confirmCode.presentation.communication

import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeEffect
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeState
import com.ewingelen.chatter.auth.core.presentation.OnVerificationStateChanged
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.VerificationErrorMapper
import com.ewingelen.chatter.core.presentation.BaseCommunication
import com.ewingelen.chatter.core.presentation.ObserveUi
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface ConfirmCodeCommunication : OnVerificationStateChanged,
    ObserveUi<ConfirmCodeState, ConfirmCodeEffect> {

    fun showPhoneNumber(phoneNumber: String)

    fun changeCode(newCode: String)

    fun stopLoading()

    fun resentCode(verifyPhoneNumber: VerifyPhoneNumber)

    class Base @Inject constructor(
        private val verificationErrorMapper: VerificationErrorMapper,
    ) : BaseCommunication<ConfirmCodeState, ConfirmCodeEffect>(ConfirmCodeState()),
        ConfirmCodeCommunication,
        ConfirmCodeResultCommunication,
        ResentCodeTimerCommunication {

        override fun showPhoneNumber(phoneNumber: String) {
            state.update { it.copy(phoneNumber = phoneNumber) }
        }

        override fun changeCode(newCode: String) {
            state.update { it.copy(code = newCode, error = "") }
        }

        override fun successSignUp() {
            effect.trySend(ConfirmCodeEffect.SuccessSignUp())
        }

        override fun successLogIn() {
            effect.trySend(ConfirmCodeEffect.SuccessLogIn())
        }

        override fun showError(error: String) {
            state.update { it.copy(error = error) }
        }

        override fun stopLoading() {
            state.update { it.copy(loading = false) }
        }

        override fun resentCode(verifyPhoneNumber: VerifyPhoneNumber) {
            state.update { it.copy(resentCodeEnabled = false) }
            effect.trySend(ConfirmCodeEffect.CodeResent(verifyPhoneNumber))
        }

        override fun onVerificationCompleted() {
            effect.trySend(ConfirmCodeEffect.SuccessSignUp())
        }

        override fun onVerificationFailed(e: Exception) {
            val error = verificationErrorMapper.map(e)
            state.update { it.copy(error = error) }
        }

        override fun onResentCodeTimerTick(timeToResent: String) {
            state.update { it.copy(timeToResent = timeToResent) }
        }

        override fun onResentCodeTimerFinish(resentCodeLabel: String) {
            state.update { it.copy(timeToResent = resentCodeLabel, resentCodeEnabled = true) }
        }

        override fun state() = state.asStateFlow()

        override fun effect() = effect.consumeAsFlow()
    }
}