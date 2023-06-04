package com.ewingelen.chatter.auth.confirmCode.presentation.communication

interface ResentCodeTimerCommunication {

    fun onResentCodeTimerTick(timeToResent: String)

    fun onResentCodeTimerFinish(resentCodeLabel: String)
}