package com.ewingelen.chatter.auth.confirmCode.presentation.communication

import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeEffect
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeState
import com.ewingelen.chatter.auth.core.presentation.OnVerificationStateChanged
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.VerificationErrorMapper
import com.ewingelen.chatter.core.presentation.BaseEffectCommunication
import com.ewingelen.chatter.core.presentation.ObserveUi
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface ConfirmCodeCommunication : ObserveUi<ConfirmCodeState, ConfirmCodeEffect>,
    OnVerificationStateChanged {

    fun showPhoneNumber(phoneNumber: String)

    fun changeCode(newCode: String)

    fun stopLoading()

    fun resentCode(verifyPhoneNumber: VerifyPhoneNumber)

    @ViewModelScoped
    class Base @Inject constructor(
        private val verificationErrorMapper: VerificationErrorMapper,
    ) : BaseEffectCommunication<ConfirmCodeState, ConfirmCodeEffect>(defaultState = ConfirmCodeState()),
        ResentCodeTimerCommunication,
        ConfirmCodeResultCommunication,
        ConfirmCodeCommunication {

        override fun showPhoneNumber(phoneNumber: String) {
            updateState(state.value.copy(phoneNumber = phoneNumber))
        }

        override fun changeCode(newCode: String) {
            updateState(state.value.copy(code = newCode, errorVisible = false))
        }

        override fun successAuth(userExists: Boolean) {
            val effect = if (userExists) {
                ConfirmCodeEffect.SuccessLogIn()
            } else {
                ConfirmCodeEffect.SuccessSignUp()
            }
            sendEffect(effect)
        }

        override fun showError(error: String) {
            updateState(state.value.copy(error = error, errorVisible = true))
        }

        override fun stopLoading() {
            updateState(state.value.copy(loading = false))
        }

        override fun resentCode(verifyPhoneNumber: VerifyPhoneNumber) {
            updateState(state.value.copy(resentCodeEnabled = false))
            sendEffect(ConfirmCodeEffect.CodeResent(verifyPhoneNumber))
        }

        override fun onVerificationCompleted() {
            sendEffect(ConfirmCodeEffect.SuccessSignUp())
        }

        override fun onVerificationFailed(e: Exception) {
            val error = verificationErrorMapper.map(e)
            updateState(state.value.copy(error = error))
        }

        override fun onResentCodeTimerTick(timeToResent: String) {
            updateState(state.value.copy(timeToResent = timeToResent))
        }

        override fun onResentCodeTimerFinish(resentCodeLabel: String) {
            updateState(state.value.copy(timeToResent = resentCodeLabel, resentCodeEnabled = true))
        }
    }
}