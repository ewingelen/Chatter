package com.ewingelen.chatter.core.presentation

import android.content.Context
import com.ewingelen.chatter.core.domain.ProvideResources
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BaseProvideResources @Inject constructor(
    @ApplicationContext private val context: Context
) : ProvideResources {

    override fun string(id: Int, vararg formatArgs: Any) = context.getString(id, *formatArgs)
}