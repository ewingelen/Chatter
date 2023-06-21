package com.ewingelen.chatter.chats.create.domain

import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.HandleDomainError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.UserNotFoundException
import javax.inject.Inject

class HandleCreateChatDomainError @Inject constructor(
    private val provideResources: ProvideResources
) : HandleDomainError<String> {

    override fun handle(e: Exception): String {
        val error = when (e) {
            is UserNotFoundException -> R.string.error_create_chat_user_not_found
            is NoInternetConnectionException -> R.string.error_create_chat_no_internet_connection
            else -> R.string.error_something_went_wrong
        }
        return provideResources.string(error)
    }
}