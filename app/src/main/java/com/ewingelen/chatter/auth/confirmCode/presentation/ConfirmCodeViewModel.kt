package com.ewingelen.chatter.auth.confirmCode.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeInteractor
import com.ewingelen.chatter.auth.core.presentation.OnVerificationStateChanged
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.auth.verifyPhone.presentation.VerificationErrorMapper
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.presentation.Action
import com.ewingelen.chatter.core.presentation.BaseEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
@HiltViewModel
class ConfirmCodeViewModel @Inject constructor(
    private val confirmCode: ConfirmCode,
    private val provideResources: ProvideResources,
    private val timer: ResentCodeTimer,
    private val verificationErrorMapper: VerificationErrorMapper,
    private val interactor: ConfirmCodeInteractor,
    savedStateHandle: SavedStateHandle,
) : BaseEffectViewModel<ConfirmCodeState, HandleConfirmCodeAction, HandleConfirmCodeEffect>
    (defaultState = ConfirmCodeState()),
    HandleConfirmCodeAction,
    HandleResentCodeTimerEvent,
    OnVerificationStateChanged {

    private val arguments = ConfirmCodeArgs(savedStateHandle)

    init {
        updateState(
            state.value.copy(
                verificationId = arguments.verificationId,
                phoneNumber = arguments.phoneNumber
            )
        )
        timer.start(viewModelScope, handleTimerEvent = this)
    }

    override fun handleAction(action: Action<HandleConfirmCodeAction>) {
        action.handle(this)
    }

    override fun changeCode(newCode: String) {
        if (newCode.length <= CODE_LENGTH) {
            updateState(state.value.copy(code = newCode))
        }

        if (newCode.length == CODE_LENGTH) {
            updateState(state.value.copy(loading = true))
            viewModelScope.launch {
                interactor.auth(
                    block = {
                        confirmCode.confirm(state.value.verificationId, state.value.code).user!!.uid
                    },
                    onSuccess = {
                        sendEffect(this, ConfirmCodeEffect.AuthSuccess())
                    },
                    onFailure = { error ->
                        updateState(state.value.copy(loading = false, error = error))
                    }
                )
            }
        }
    }

    override fun resentCode() {
        updateState(state.value.copy(resentCodeEnabled = false))
        sendEffect(
            ConfirmCodeEffect.ResentCode(
                VerifyPhoneNumber.Base(arguments.phoneNumber, onVerificationStateChanged = this)
            )
        )
        timer.start(viewModelScope, handleTimerEvent = this)
    }

    override fun onResentCodeTimerTick(secondsUntilFinished: Long) {
        val timeToResentLabel = provideResources.string(
            R.string.label_format_try_again,
            secondsUntilFinished
        )
        updateState(state.value.copy(timeToResentLabel = timeToResentLabel))
    }

    override fun onResentCodeTimerFinish() {
        val labelResentCode = provideResources.string(R.string.label_clickable_resent_code)
        updateState(state.value.copy(timeToResentLabel = labelResentCode, resentCodeEnabled = true))
    }

    override fun onVerificationCompleted() {
        sendEffect(ConfirmCodeEffect.AuthSuccess())
    }

    override fun onVerificationFailed(e: Exception) {
        val error = verificationErrorMapper.map(e)
        updateState(state.value.copy(error = error))
    }

    private companion object {
        const val CODE_LENGTH = 6
    }
}