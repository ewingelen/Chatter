package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.core.presentation.Action

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
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