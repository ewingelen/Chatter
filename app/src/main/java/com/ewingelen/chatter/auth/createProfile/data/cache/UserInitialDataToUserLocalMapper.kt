package com.ewingelen.chatter.auth.createProfile.data.cache

import android.net.Uri
import com.ewingelen.chatter.auth.createProfile.data.ProvidePhotoPath
import com.ewingelen.chatter.auth.createProfile.data.UserInitialData
import com.ewingelen.chatter.core.data.cache.SavePhoto
import com.ewingelen.chatter.core.data.cache.model.UserLocal
import javax.inject.Inject

class UserInitialDataToUserLocalMapper @Inject constructor(
    private val savePhoto: SavePhoto,
    private val providePhotoPath: ProvidePhotoPath
) : UserInitialData.Mapper<UserLocal> {

    override suspend fun map(
        id: String,
        phoneNumber: String,
        name: String,
        surname: String,
        photo: Uri
    ): UserLocal {
        //TODO: check
        val photoFilePath = providePhotoPath.provide(photo) {
            val filePath = "$AVATARS_PATH/$id"
            savePhoto.save(photo, id)
            filePath
        }
        return UserLocal(
            id = id,
            phoneNumber = phoneNumber,
            name = name,
            surname = surname,
            photoFilePath = photoFilePath
        )
    }

    private companion object {
        const val AVATARS_PATH = "avatars"
    }
}