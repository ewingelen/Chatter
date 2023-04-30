package com.ewingelen.chatter.core

import android.app.Application
import com.ewingelen.chatter.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //TODO: initializer
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}