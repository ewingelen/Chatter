package com.ewingelen.chatter.createProfile.presentation

import android.net.Uri

interface HandleCreateProfileAction {

    fun changeName(newName: String)

    fun addPhoto(newPhoto: Uri)

    fun createProfile()
}