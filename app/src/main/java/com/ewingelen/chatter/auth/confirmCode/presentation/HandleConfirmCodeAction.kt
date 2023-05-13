package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.core.presentation.HandleAction

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface HandleConfirmCodeAction: HandleAction {

    fun changeCode(newCode: String)

    fun resentCode()
}