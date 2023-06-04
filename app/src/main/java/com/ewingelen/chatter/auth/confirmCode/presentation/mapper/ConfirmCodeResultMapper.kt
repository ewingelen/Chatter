package com.ewingelen.chatter.auth.confirmCode.presentation.mapper

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeResult
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ConfirmCodeResultCommunication
import javax.inject.Inject

class ConfirmCodeResultMapper @Inject constructor(
    private val communication: ConfirmCodeResultCommunication,
) : ConfirmCodeResult.Mapper<Unit> {

    override fun map(newUser: Boolean) {
        if (newUser) communication.successSignUp() else communication.successLogIn()
    }

    override fun map(error: String) {
        communication.showError(error)
    }
}