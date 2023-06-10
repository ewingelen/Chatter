package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import com.ewingelen.chatter.core.data.HandleDataError
import javax.inject.Inject

class BaseConfirmCodeRepository @Inject constructor(
    private val cloudDataSource: ConfirmCodeCloudDataSource,
    private val handleCheckUserNewError: HandleDataError
): ConfirmCodeRepository {

    override suspend fun checkUserExists() = handleCheckUserNewError.handle {
        cloudDataSource.checkUserExists()
    }
}