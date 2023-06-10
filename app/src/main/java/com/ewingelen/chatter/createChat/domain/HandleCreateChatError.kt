package com.ewingelen.chatter.createChat.domain

import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.domain.ProvideResources
import javax.inject.Inject

class HandleCreateChatError @Inject constructor(
    private val provideResources: ProvideResources
) : HandleDataError {

//    override fun handle(e: Exception): String {
//        val errorResourceId = when (e) {
//            is NoInternetConnectionException -> R.string.error_no_internet_connection
//            is NoSuchUserException -> R.string.error_user_not_found
//            else -> R.string.error_something_went_wrong
//        }
//        return provideResources.string(errorResourceId)
//    }

    override suspend fun <T> handle(block: suspend () -> T): T {
        TODO("Not yet implemented")
    }
}