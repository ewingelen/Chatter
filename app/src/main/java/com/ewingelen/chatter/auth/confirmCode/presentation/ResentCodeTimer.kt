package com.ewingelen.chatter.auth.confirmCode.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 06.05.2023.
 */
interface ResentCodeTimer {

    fun start(
        coroutineScope: CoroutineScope,
        handleTimerEvent: HandleResentCodeTimerEvent,
    )

    class Base @Inject constructor() : ResentCodeTimer {

        override fun start(
            coroutineScope: CoroutineScope,
            handleTimerEvent: HandleResentCodeTimerEvent
        ) {
            coroutineScope.launch {
                var time = RESENT_CODE_TIMEOUT
                while (time > 0) {
                    handleTimerEvent.onResentCodeTimerTick(time--)
                    delay(1000L)
                }
                handleTimerEvent.onResentCodeTimerFinish()
            }
        }

        private companion object {
            const val RESENT_CODE_TIMEOUT = 60L
        }
    }
}