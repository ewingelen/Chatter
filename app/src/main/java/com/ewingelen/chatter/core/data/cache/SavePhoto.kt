package com.ewingelen.chatter.core.data.cache
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject

interface SavePhoto {

    fun save(uri: Uri, fileName: String)

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : SavePhoto {

        override fun save(uri: Uri, fileName: String) {
            val bitmap = context.contentResolver.openInputStream(uri).use { stream ->
                BitmapFactory.decodeStream(stream)
            }
            context.openFileOutput("$fileName.jpg", Context.MODE_PRIVATE).use { stream ->
                if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                    throw IOException("Couldn't save bitmap")
                }
            }
        }
    }
}