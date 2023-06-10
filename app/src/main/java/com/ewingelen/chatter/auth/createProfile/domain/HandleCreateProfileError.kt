package com.ewingelen.chatter.auth.createProfile.domain

import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.domain.HandleDomainError
import com.ewingelen.chatter.core.domain.NoInternetConnectionException
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.ServerNotAvailableException
import javax.inject.Inject

class HandleCreateProfileError @Inject constructor(
    private val provideResources: ProvideResources
): HandleDomainError<String> {

    override fun handle(e: Exception): String {
        val errorResourceId = when (e) {
            is NoInternetConnectionException -> R.string.error_create_profile_no_internet_connection
            is ServerNotAvailableException -> R.string.error_server_is_not_available
            else -> R.string.error_something_went_wrong
        }
        return provideResources.string(errorResourceId)
    }
}