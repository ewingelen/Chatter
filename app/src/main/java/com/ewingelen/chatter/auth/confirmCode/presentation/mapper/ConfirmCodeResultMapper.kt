package com.ewingelen.chatter.auth.confirmCode.presentation.mapper

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeResult
import com.ewingelen.chatter.auth.confirmCode.presentation.communication.ConfirmCodeResultCommunication
import javax.inject.Inject

class ConfirmCodeResultMapper @Inject constructor(
    private val communication: ConfirmCodeResultCommunication
) : ConfirmCodeResult.Mapper {

    override fun map(userExists: Boolean) {
        communication.successAuth(userExists)
    }

    override fun map(error: String) {
        communication.showError(error)
    }
}