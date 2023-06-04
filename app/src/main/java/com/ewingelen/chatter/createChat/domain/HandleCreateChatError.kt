package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.domain.HandleError
import com.ewingelen.chatter.core.domain.ProvideResources
import javax.inject.Inject


class HandleCreateChatError @Inject constructor(
    private val provideResources: ProvideResources
) : HandleError<String> {

//    override fun handle(e: Exception): String {
//        val errorResourceId = when (e) {
//            is NoInternetConnectionException -> R.string.error_no_internet_connection
//            is NoSuchUserException -> R.string.error_user_not_found
//            else -> R.string.error_something_went_wrong
//        }
//        return provideResources.string(errorResourceId)
//    }

    override suspend fun handle(block: suspend () -> Unit): String {
        TODO("Not yet implemented")
    }
}