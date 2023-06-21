package com.ewingelen.chatter.core.initializer

import android.content.Context
import androidx.startup.Initializer

@Suppress("UNUSED")
class OneSignalInitializer : Initializer<Unit> {

    override fun create(context: Context) {
//        OneSignal.initWithContext(context)
//        OneSignal.setAppId(BuildConfig.ONE_SIGNAL_ID)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}