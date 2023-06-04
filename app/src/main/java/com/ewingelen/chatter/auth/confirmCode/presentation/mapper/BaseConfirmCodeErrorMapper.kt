package com.ewingelen.chatter.auth.confirmCode.presentation.mapper

import com.ewingelen.chatter.R
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeErrorMapper
import com.ewingelen.chatter.core.domain.ProvideResources
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import javax.inject.Inject

class BaseConfirmCodeErrorMapper @Inject constructor(
    private val provideResources: ProvideResources
) : ConfirmCodeErrorMapper {

    override fun map(e: Exception): String {
        val errorResourceId = when (e) {
            is FirebaseAuthInvalidCredentialsException -> R.string.error_invalid_code
            is FirebaseNetworkException -> R.string.error_no_internet_connection
            else -> R.string.error_something_went_wrong
        }
        return provideResources.string(errorResourceId)
    }
}