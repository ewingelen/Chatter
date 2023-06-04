package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import javax.inject.Inject

class BaseConfirmCodeRepository @Inject constructor(
    private val cloudDataSource: ConfirmCodeCloudDataSource
): ConfirmCodeRepository {

    override suspend fun addUser() {
        cloudDataSource.addUser()
    }
}