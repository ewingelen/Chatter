package com.ewingelen.chatter.auth.createProfile.domain

interface CreateProfileRepository {

    suspend fun createProfile(userInitial: UserInitial)
}