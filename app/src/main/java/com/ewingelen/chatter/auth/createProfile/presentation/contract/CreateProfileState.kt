package com.ewingelen.chatter.auth.createProfile.presentation.contract

import android.net.Uri
import com.ewingelen.chatter.auth.createProfile.domain.UserInitial

interface CreateProfileState {

    fun userInitial(): UserInitial.Base

    data class Base(
        val name: String = "",
        val surname: String = "",
        val photo: Uri = Uri.EMPTY,
        val loading: Boolean = false,
        val noInternetWarningVisible: Boolean = false,
        val createProfileError: String = "",
        val createProfileErrorVisible: Boolean = false,
        val emptyNameErrorShowing: Boolean = false
    ) : CreateProfileState {

        override fun userInitial() =
            UserInitial.Base(name = name, surname = surname, photo = photo)
    }
}