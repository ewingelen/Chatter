package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.HandleError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.ProvideResources
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
class HandleCreateChatError @Inject constructor(
    private val provideResources: ProvideResources
) : HandleError {

    override fun handle(e: Exception): String {
        val errorResourceId = when (e) {
            is NoInternetConnectionException -> R.string.error_no_internet_connection
            is NoSuchUserException -> R.string.error_user_not_found
            else -> R.string.error_something_went_wrong
        }
        return provideResources.string(errorResourceId)
    }
}