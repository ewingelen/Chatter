package com.ewingelen.chatter.auth.confirmCode.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 08.05.2023.
 */
interface ConfirmCodeErrorMapper {

    fun map(e: Exception): String
}