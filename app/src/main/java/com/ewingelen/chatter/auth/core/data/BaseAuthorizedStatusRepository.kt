package com.ewingelen.chatter.auth.core.data

import com.ewingelen.chatter.auth.core.domain.AuthorizedStatusRepository
import javax.inject.Inject

class BaseAuthorizedStatusRepository @Inject constructor(
    private val cacheDataSource: AuthorizedStatusCacheDataSource
) : AuthorizedStatusRepository.Save, AuthorizedStatusRepository.Read {

    override suspend fun authorize() {
        cacheDataSource.authorize()
    }

    override suspend fun checkUserAuthorized() = cacheDataSource.checkUserAuthorized()
}