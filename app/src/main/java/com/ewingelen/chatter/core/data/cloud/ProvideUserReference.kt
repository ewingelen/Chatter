package com.ewingelen.chatter.core.data.cloud

import com.ewingelen.chatter.core.data.cloud.model.ProvideUserId
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 16.05.2023.
 */
interface ProvideUserReference {

    interface Collection {

        fun collection(): CollectionReference
    }

    interface Document {

        fun document(): DocumentReference
    }

    interface All : Collection, Document

    class Base @Inject constructor(private val provideUserId: ProvideUserId) : All {

        private val collection = Firebase.firestore.collection(COLLECTION_PATH)

        override fun collection() = collection

        override fun document() = collection.document(provideUserId.provide())

        private companion object {
            const val COLLECTION_PATH = "users"
        }
    }
}