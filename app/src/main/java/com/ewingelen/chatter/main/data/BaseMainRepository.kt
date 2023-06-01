package com.ewingelen.chatter.main.data

import com.ewingelen.chatter.auth.confirmCode.data.cache.ConfirmCodeCacheDataSource
import com.ewingelen.chatter.main.domain.MainRepository
import javax.inject.Inject

class BaseMainRepository @Inject constructor(
    private val cacheDataSource: ConfirmCodeCacheDataSource
): MainRepository {

    override suspend fun checkUserAuthorized() = cacheDataSource.checkUserAuthorized()
}