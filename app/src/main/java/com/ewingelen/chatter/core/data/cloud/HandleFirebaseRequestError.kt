package com.ewingelen.chatter.core.data.cloud

import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.ServerNotAvailableException
import com.ewingelen.chatter.core.domain.UnknownException
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseNetworkException
import javax.inject.Inject

class HandleFirebaseRequestError @Inject constructor() : HandleDataError {

    override suspend fun <T> handle(block: suspend () -> T) =
        try {
            block()
        } catch (e: Exception) {
            val error = when (e) {
                is FirebaseNetworkException -> NoInternetConnectionException()
                is FirebaseApiNotAvailableException -> ServerNotAvailableException()
                else -> UnknownException()
            }
            throw error
        }
}