package com.ewingelen.chatter.core.domain

import androidx.annotation.StringRes

interface ProvideResources {

    fun string(@StringRes id: Int, vararg formatArgs: Any): String
}