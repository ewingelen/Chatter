package com.ewingelen.chatter.auth.createProfile.data

import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface UserInitialData {

    interface Mapper<T> {

        suspend fun map(
            id: String,
            phoneNumber: String,
            name: String,
            surname: String,
            photo: Uri
        ): T
    }

    suspend fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val id: String = Firebase.auth.uid!!,
        private val phoneNumber: String = Firebase.auth.currentUser!!.phoneNumber!!,
        private val name: String,
        private val surname: String,
        private val photo: Uri
    ) : UserInitialData {

        override suspend fun <T> map(mapper: Mapper<T>) = mapper.map(
            id = id,
            phoneNumber = phoneNumber,
            name = name,
            surname = surname,
            photo = photo
        )
    }
}