package com.ewingelen.chatter.createProfile.domain

import android.net.Uri

interface CreateProfileRepository {

    suspend fun createProfile(name: String)

    suspend fun addPhoto(photo: Uri)
}