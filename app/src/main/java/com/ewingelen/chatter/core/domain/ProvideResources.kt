package com.ewingelen.chatter.core.domain

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ProvideResources {

    fun string(@StringRes id: Int, vararg formatArgs: Any): String

    fun plural(@PluralsRes id: Int, quantity: Int, vararg formatArgs: Any): String
}