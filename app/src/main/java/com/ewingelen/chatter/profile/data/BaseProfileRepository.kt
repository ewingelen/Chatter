package com.ewingelen.chatter.profile.data

import com.ewingelen.chatter.auth.core.data.AuthorizedStatusCacheDataSource
import com.ewingelen.chatter.profile.domain.ProfileRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class BaseProfileRepository @Inject constructor(
    private val cloudDataSource: ProfileCloudDataSource,
    private val cacheDataSource: AuthorizedStatusCacheDataSource
) : ProfileRepository {

    override suspend fun fetchInfo() = cloudDataSource.fetchInfo()

    override suspend fun signOut() {
        Firebase.auth.signOut()
        cacheDataSource.signOut()
    }
}