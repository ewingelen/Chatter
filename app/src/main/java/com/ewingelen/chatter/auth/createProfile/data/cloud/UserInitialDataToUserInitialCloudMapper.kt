package com.ewingelen.chatter.auth.createProfile.data.cloud

import android.net.Uri
import com.ewingelen.chatter.auth.createProfile.data.ProvidePhotoPath
import com.ewingelen.chatter.auth.createProfile.data.UserInitialData
import com.ewingelen.chatter.core.data.cloud.UploadPhoto
import javax.inject.Inject

//TODO: check save avatar
class UserInitialDataToUserInitialCloudMapper @Inject constructor(
    private val uploadPhoto: UploadPhoto,
    private val providePhotoPath: ProvidePhotoPath
) : UserInitialData.Mapper<UserInitialCloud> {

    override suspend fun map(
        id: String,
        phoneNumber: String,
        name: String,
        surname: String,
        photo: Uri
    ): UserInitialCloud {
        val avatarUrl = providePhotoPath.provide(photo) {
            uploadPhoto.uload(USER_AVATARS_LOCATION, id, uri = photo).toString()
        }
        return UserInitialCloud(
            id = id,
            phoneNumber = phoneNumber,
            name = name,
            surname = surname,
            avatarUrl = avatarUrl
        )
    }

    private companion object {
        const val USER_AVATARS_LOCATION = "users_avatars"
    }
}