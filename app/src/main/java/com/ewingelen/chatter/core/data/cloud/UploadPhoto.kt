package com.ewingelen.chatter.core.data.cloud

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface UploadPhoto {

    suspend fun uload(vararg paths: String, uri: Uri): Uri

    class Base @Inject constructor() : UploadPhoto {

        override suspend fun uload(vararg paths: String, uri: Uri): Uri {
            val location = paths.joinToString("/")
            val uploadTask = Firebase.storage.getReference(location).putFile(uri).await()
            return uploadTask.metadata!!.reference!!.downloadUrl.await()
        }
    }
}