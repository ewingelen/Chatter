package com.ewingelen.chatter.core.data.cloud

import com.ewingelen.chatter.core.domain.HandleError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.ServerNotAvailableException
import com.ewingelen.chatter.core.domain.UnknownException
import com.google.firebase.FirebaseApiNotAvailableException
import com.google.firebase.FirebaseNetworkException
import javax.inject.Inject

class HandleFirebaseRequestError @Inject constructor() : HandleError<Unit> {

    override suspend fun handle(block: suspend () -> Unit) {
        return try {
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
}