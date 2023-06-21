package com.ewingelen.chatter.chat.data

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

interface ProvideFileNameByUri {

    fun provide(uri: Uri): String

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ProvideFileNameByUri {

        override fun provide(uri: Uri): String {
            Timber.d(uri.toString())
            val returnCursor = context.contentResolver.query(uri, null, null, null, null)!!
            val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            returnCursor.moveToFirst()
            val name = returnCursor.getString(nameIndex)
            returnCursor.close()
            return name
        }
    }
}