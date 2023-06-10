package com.ewingelen.chatter.auth.confirmCode.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeInteractor
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeResult
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ConfirmCodeCommunication
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeAction
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeEffect
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.ConfirmCodeState
import com.ewingelen.chatter.auth.confirmCode.presentation.contract.HandleConfirmCodeAction
import com.ewingelen.chatter.auth.core.presentation.VerifyPhoneNumber
import com.ewingelen.chatter.core.presentation.BaseNewEffectViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: refactor
@HiltViewModel
class ConfirmCodeViewModel @Inject constructor(
    private val confirmCode: ConfirmCode,
    private val timer: ResentCodeTimer,
    private val interactor: ConfirmCodeInteractor,
    private val communication: ConfirmCodeCommunication,
    private val confirmCodeResultMapper: ConfirmCodeResult.Mapper,
    savedStateHandle: SavedStateHandle,
) : BaseNewEffectViewModel<ConfirmCodeState, ConfirmCodeAction, ConfirmCodeEffect>(
    communication
), HandleConfirmCodeAction {

    private val arguments = ConfirmCodeArgs(savedStateHandle)

    init {
        communication.showPhoneNumber(arguments.phoneNumber)
        timer.start()
    }

    override fun handleAction(action: ConfirmCodeAction) {
        action.handle(this)
    }

    override fun changeCode(newCode: String) {
        if (newCode.length <= CODE_LENGTH) {
            communication.changeCode(newCode)
        }

        if (newCode.length == CODE_LENGTH) {
            communication.stopLoading()
            viewModelScope.launch {
                interactor.confirmCode {
                    confirmCode.confirm(arguments.verificationId, newCode)
                }.map(confirmCodeResultMapper)
            }
        }
    }

    override fun resentCode() {
        val verifyPhoneNumber = VerifyPhoneNumber.Base(
            onVerificationStateChanged = communication,
            arguments.phoneNumber
        )
        communication.resentCode(verifyPhoneNumber)
        timer.start()
    }

    private companion object {
        const val CODE_LENGTH = 6
    }
}