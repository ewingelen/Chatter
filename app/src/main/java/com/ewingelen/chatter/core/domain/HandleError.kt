package com.ewingelen.chatter.core.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface HandleError {

    fun handle(e: Exception): String
}