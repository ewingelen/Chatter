package com.ewingelen.chatter.auth.createProfile.presentation.contract

import android.net.Uri

interface HandleCreateProfileAction {

    fun changeName(newName: String)

    fun changeSurname(newSurname: String)

    fun addPhoto(newPhoto: Uri)

    fun createProfile()
}