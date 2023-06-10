package com.ewingelen.chatter.auth.createProfile.data

import android.net.Uri
import com.ewingelen.chatter.auth.createProfile.domain.UserInitial
import javax.inject.Inject

class UserInitialMapper @Inject constructor(): UserInitial.Mapper<UserInitialData> {

    override fun map(name: String, surname: String, photo: Uri) =
        UserInitialData.Base(name = name, surname = surname, photo = photo)
}