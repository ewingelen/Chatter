package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.auth.confirmCode.data.cache.ConfirmCodeCacheDataSource
import com.ewingelen.chatter.auth.confirmCode.data.cloud.ConfirmCodeCloudDataSource
import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
class BaseConfirmCodeRepository @Inject constructor(
    private val cloudDataSource: ConfirmCodeCloudDataSource,
    private val cacheDataSource: ConfirmCodeCacheDataSource,
) : ConfirmCodeRepository {

    override suspend fun saveUser() {
        val userId = Firebase.auth.uid!!
        cacheDataSource.saveUser(userId)
    }

    override suspend fun checkUserExists() = cloudDataSource.checkUserExists()
}