package com.ewingelen.chatter.createProfile.data

import android.net.Uri
import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.SavePhoto
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface CreateProfileCloudDataSource {

    suspend fun createProfile(name: String)

    suspend fun addPhoto(photo: Uri)

    class Base @Inject constructor(
        private val savePhoto: SavePhoto,
        provideUserReference: ProvideUserReference,
    ) : CreateProfileCloudDataSource {

        private val userDocument = provideUserReference.document()
        private val userId = Firebase.auth.uid!!

        override suspend fun createProfile(name: String) {
            //TODO: error case
            val user = mapOf("id" to userId, "name" to name)
            userDocument.set(user)
        }

        override suspend fun addPhoto(photo: Uri) {
            //TODO: error case
            val photoUrl = savePhoto.save(USER_PHOTOS_LOCATION, userId, uri = photo)
            userDocument.set(mapOf("photo" to photoUrl))
        }

        private companion object {
            const val USER_PHOTOS_LOCATION = "users_photos"
        }
    }
}