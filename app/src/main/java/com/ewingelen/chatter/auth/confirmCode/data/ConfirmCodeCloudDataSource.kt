package com.ewingelen.chatter.auth.confirmCode.data

import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConfirmCodeCloudDataSource {

    suspend fun addUser()

    class Base @Inject constructor(
        private val provideUserReference: ProvideUserReference.Document
    ) : ConfirmCodeCloudDataSource {

        override suspend fun addUser() {
            val userData = mapOf(
                ID_FIELD to Firebase.auth.uid,
                PHONE_NUMBER_FIELD to Firebase.auth.currentUser!!.phoneNumber
            )
            provideUserReference.document().set(userData).await()
        }

        private companion object {
            const val ID_FIELD = "id"
            const val PHONE_NUMBER_FIELD = "phone_number"
        }
    }
}