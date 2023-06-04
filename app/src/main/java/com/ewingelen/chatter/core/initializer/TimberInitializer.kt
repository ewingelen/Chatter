package com.ewingelen.chatter.core.initializer

import android.content.Context
import androidx.startup.Initializer
import com.ewingelen.chatter.BuildConfig
import timber.log.Timber

@Suppress("UNUSED")
class TimberInitializer: Initializer<Unit> {

    override fun create(context: Context) {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}