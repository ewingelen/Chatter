package com.ewingelen.chatter.auth.verifyPhone.presentation

import android.content.Context
import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.UnknownException
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface VerificationErrorMapper {

    fun map(e: FirebaseException): Exception

    class Base(private val context: Context) : VerificationErrorMapper {

        override fun map(e: FirebaseException) = when (e) {
            is FirebaseAuthInvalidCredentialsException -> InvalidPhoneNumberException(context)
            is FirebaseTooManyRequestsException -> TooManyRequestsException(context)
            is FirebaseNetworkException -> NoInternetConnectionException(context)
            else -> UnknownException(context)
        }
    }
}

class TooManyRequestsException(private val context: Context) : Exception("Too many requests") {

    override fun getLocalizedMessage() = context.getString(R.string.label_too_many_requests)
}

class InvalidPhoneNumberException(
    private val context: Context
) : Exception("Invalid phone number") {

    override fun getLocalizedMessage() = context.getString(R.string.label_invalid_phone_number)
}