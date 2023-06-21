package com.ewingelen.chatter.chats.create.data

import com.ewingelen.chatter.core.data.cloud.CheckInternetConnection
import com.ewingelen.chatter.core.data.cloud.ProvideUserReference
import com.ewingelen.chatter.core.data.cloud.model.ChatCloud
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import javax.inject.Inject

interface CreateChatCloudDataSource {

    suspend fun createChat(chat: ChatCloud, savedContact: SavedContactCloud)

    class Base @Inject constructor(
        private val provideUserReference: ProvideUserReference.All,
        private val checkInternetConnection: CheckInternetConnection
    ) : CreateChatCloudDataSource {

        override suspend fun createChat(chat: ChatCloud, savedContact: SavedContactCloud) {
            val usersCollection = provideUserReference.collection()
            when {
                !checkInternetConnection.available() -> {
                    throw FirebaseNetworkException("No internet connection")
                }

                !chat.userExists(usersCollection, savedContact.contactPhoneNumber) -> {
                    throw FirebaseFirestoreException(
                        "User with this phone number not found",
                        FirebaseFirestoreException.Code.NOT_FOUND
                    )
                }

                else -> {
                    provideUserReference.document().update(
                        "savedContacts",
                        FieldValue.arrayUnion(savedContact)
                    )
                    Timber.d(chat.id + " CHAT ID")
                    Firebase.firestore.collection(CHATS_FIELD).document(chat.id).set(chat)
                }
            }
        }

        private companion object {
            const val CHATS_FIELD = "chats"
        }
    }
}