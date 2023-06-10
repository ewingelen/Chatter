package com.ewingelen.chatter.auth.confirmCode.presentation

import android.os.CountDownTimer
import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ResentCodeTimerCommunication
import com.ewingelen.chatter.core.domain.ProvideResources
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ResentCodeTimer @Inject constructor(
    private val provideResources: ProvideResources,
    private val communication: ResentCodeTimerCommunication
) : CountDownTimer(FULL_TIME, TICK_DELAY) {

    override fun onTick(millisUntilFinished: Long) {
        val timeLeftLabel = provideResources.string(
            R.string.label_format_try_again,
            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
        )
        communication.onResentCodeTimerTick(timeLeftLabel)
    }

    override fun onFinish() {
        val resentCodeLabel = provideResources.string(R.string.label_clickable_resent_code)
        communication.onResentCodeTimerFinish(resentCodeLabel)
    }

    private companion object {
        const val FULL_TIME = 60000L
        const val TICK_DELAY = 1000L
    }
}