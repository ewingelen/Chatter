package com.ewingelen.chatter.auth.createProfile.presentation

import com.ewingelen.chatter.auth.createProfile.domain.CreateProfileResult
import javax.inject.Inject

class CreateProfileResultMapper @Inject constructor(
    private val communication: CreateProfileCommunication
) : CreateProfileResult.Mapper {

    override fun map() {
        communication.profileCreated()
    }

    override fun map(error: String) {
        communication.showCreateProfileError(error)
    }
}