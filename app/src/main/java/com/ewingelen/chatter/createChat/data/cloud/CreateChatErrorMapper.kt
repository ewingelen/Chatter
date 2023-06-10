package com.ewingelen.chatter.createChat.data.cloud

import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.UnknownException
import com.ewingelen.chatter.createChat.domain.NoSuchUserException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import javax.inject.Inject

interface CreateChatErrorMapper {

    fun map(e: FirebaseException): Exception

    class Base @Inject constructor(): CreateChatErrorMapper {

        override fun map(e: FirebaseException) = when (e) {
            is FirebaseNetworkException -> NoInternetConnectionException()
            is UserNotFoundException -> NoSuchUserException()
            else -> UnknownException()
        }
    }
}
