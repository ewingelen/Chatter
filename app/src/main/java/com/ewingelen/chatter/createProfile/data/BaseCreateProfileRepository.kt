package com.ewingelen.chatter.createProfile.data

import android.net.Uri
import com.ewingelen.chatter.createProfile.domain.CreateProfileRepository
import javax.inject.Inject

class BaseCreateProfileRepository @Inject constructor(
    private val createProfileCloudDataSource: CreateProfileCloudDataSource,
) : CreateProfileRepository {

    override suspend fun createProfile(name: String) {
        try {
            createProfileCloudDataSource.createProfile(name)
        } catch (e: Exception) {
        }
    }

    override suspend fun addPhoto(photo: Uri) {
        createProfileCloudDataSource.addPhoto(photo)
    }
}