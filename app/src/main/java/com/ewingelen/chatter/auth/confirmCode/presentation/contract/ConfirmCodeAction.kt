package com.ewingelen.chatter.auth.confirmCode.presentation.contract

import com.ewingelen.chatter.core.presentation.Action

interface ConfirmCodeAction : Action<HandleConfirmCodeAction> {

    class ChangeCode(private val newCode: String): ConfirmCodeAction {

        override fun handle(handle: HandleConfirmCodeAction) {
            handle.changeCode(newCode)
        }
    }

    class ResentCode : ConfirmCodeAction {

        override fun handle(handle: HandleConfirmCodeAction) {
            handle.resentCode()
        }
    }
}