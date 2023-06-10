package com.ewingelen.chatter.auth.createProfile.data

import android.net.Uri
import javax.inject.Inject

interface ProvidePhotoPath {

    suspend fun provide(photo: Uri, block: suspend () -> String): String

    class Base @Inject constructor() : ProvidePhotoPath {

        override suspend fun provide(photo: Uri, block: suspend () -> String) =
            if (photo == Uri.EMPTY) "" else block()
    }
}