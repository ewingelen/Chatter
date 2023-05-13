package com.ewingelen.chatter.auth.verifyPhone.presentation

import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.ProvideResources
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 30.04.2023.
 */
interface VerificationErrorMapper {

    fun map(e: Exception): String

    class Base @Inject constructor(
        private val provideResources: ProvideResources
    ) : VerificationErrorMapper {

        override fun map(e: Exception): String {
            val errorResourceId = when (e) {
                is FirebaseAuthInvalidCredentialsException -> R.string.error_invalid_phone_number
                is FirebaseTooManyRequestsException -> R.string.error_too_many_requests
                is FirebaseNetworkException -> R.string.error_no_internet_connection
                else -> R.string.error_something_went_wrong
            }
            return provideResources.string(errorResourceId)
        }
    }
}
