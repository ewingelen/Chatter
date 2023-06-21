package com.ewingelen.chatter.profile.domain

import com.ewingelen.chatter.core.data.cloud.model.UserCloud

interface ProfileRepository {

    suspend fun fetchInfo(): UserCloud

    suspend fun signOut()
}