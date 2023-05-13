package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
class BaseConfirmCodeRepository @Inject constructor(
    private val cacheDataSource: ConfirmCodeCacheDataSource
) : ConfirmCodeRepository.Mutable {

    override suspend fun saveUser(id: String) {
        cacheDataSource.saveUser(id)
    }

    override suspend fun fetchUserId() = cacheDataSource.fetchUserId()
}