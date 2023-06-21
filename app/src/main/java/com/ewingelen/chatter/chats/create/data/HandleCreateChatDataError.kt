package com.ewingelen.chatter.chats.create.data

import com.ewingelen.chatter.core.data.HandleDataError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.UnknownException
import com.ewingelen.chatter.core.domain.UserNotFoundException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import timber.log.Timber
import javax.inject.Inject

class HandleCreateChatDataError @Inject constructor() : HandleDataError {

    override suspend fun <T> handle(block: suspend () -> T) =
        try {
            block()
        } catch (e: Exception) {
            Timber.d(e.toString())
            val error = when {
                e is FirebaseFirestoreException && e.code == FirebaseFirestoreException.Code.NOT_FOUND -> UserNotFoundException()
                e is FirebaseNetworkException -> NoInternetConnectionException()
                else -> UnknownException()
            }
            throw error
        }
}