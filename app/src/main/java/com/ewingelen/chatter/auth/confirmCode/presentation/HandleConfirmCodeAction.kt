package com.ewingelen.chatter.auth.confirmCode.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface HandleConfirmCodeAction {

    fun changeCode(newCode: String)

    fun resentCode()
}