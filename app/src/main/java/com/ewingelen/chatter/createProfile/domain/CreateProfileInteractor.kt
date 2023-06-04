package com.ewingelen.chatter.createProfile.domain

import android.net.Uri
import javax.inject.Inject

interface CreateProfileInteractor {

    suspend fun createProfile(name: String): CreateProfileResult

    suspend fun addPhoto(photo: Uri)

    class Base @Inject constructor(
        private val repository: CreateProfileRepository,
    ) : CreateProfileInteractor {

        override suspend fun createProfile(name: String) = try {
            repository.createProfile(name)
            CreateProfileResult.Success()
        } catch (e: Exception) {
            CreateProfileResult.Fail()
        }

        override suspend fun addPhoto(photo: Uri) {
            repository.addPhoto(photo)
        }
    }
}