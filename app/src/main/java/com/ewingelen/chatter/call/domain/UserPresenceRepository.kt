package com.ewingelen.chatter.call.domain

import kotlinx.coroutines.flow.Flow

interface UserPresenceRepository {

    suspend fun changeUserPresence(presence: Boolean)

    fun checkUserPresence(): Flow<Boolean>
}