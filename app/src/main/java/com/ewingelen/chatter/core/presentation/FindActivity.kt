package com.ewingelen.chatter.core.presentation

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
//TODO: research about activity in composable
fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}