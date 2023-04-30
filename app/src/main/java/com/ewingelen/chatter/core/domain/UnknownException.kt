package com.ewingelen.chatter.core.domain

import android.content.Context
import com.ewingelen.chatter.R

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
class UnknownException(private val context: Context) : Exception("Unknown error") {

    override fun getLocalizedMessage() = context.getString(R.string.label_something_went_wrong)
}