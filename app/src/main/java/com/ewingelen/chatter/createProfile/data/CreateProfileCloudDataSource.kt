package com.ewingelen.chatter.createProfile.data

import android.net.Uri
import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.SavePhoto
import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


//TODO: Unite methods, think about "UserInitial" model
interface CreateProfileCloudDataSource {

    suspend fun createProfile(name: String)

    suspend fun addPhoto(photo: Uri)

    class Base @Inject constructor(
        private val savePhoto: SavePhoto,
        provideUserReference: ProvideUserReference.Document,
        provideUserId: ProvideUserId,
    ) : CreateProfileCloudDataSource {

        private val userDocument = provideUserReference.document()
        private val userId = provideUserId.provide()

        override suspend fun createProfile(name: String) {
            val userInitial = mapOf(
                ID_FIELD to Firebase.auth.uid,
                NAME_FIELD to name,
                PHONE_NUMBER_FIELD to Firebase.auth.currentUser!!.phoneNumber,
            )
            userDocument.set(userInitial).await()
        }

        override suspend fun addPhoto(photo: Uri) {
            val photoUrl = savePhoto.save(USER_PHOTOS_LOCATION, userId, uri = photo)
            userDocument.set(mapOf(PHOTO_FIELD to photoUrl))
        }

        private companion object {
            const val USER_PHOTOS_LOCATION = "users_photos"
            const val ID_FIELD = "id"
            const val NAME_FIELD = "name"

            //TODO: find duplicate and think about solution
            const val PHONE_NUMBER_FIELD = "phone_number"
            const val PHOTO_FIELD = "photo"
        }
    }
}