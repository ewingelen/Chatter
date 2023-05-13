package com.ewingelen.chatter.auth.confirmCode.presentation

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
interface HandleResentCodeTimerEvent {

    fun onResentCodeTimerTick(secondsUntilFinished: Long)

    fun onResentCodeTimerFinish()
}