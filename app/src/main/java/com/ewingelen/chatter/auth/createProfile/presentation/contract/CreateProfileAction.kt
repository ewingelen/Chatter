package com.ewingelen.chatter.auth.createProfile.presentation.contract

import android.net.Uri
import com.ewingelen.chatter.core.presentation.Action

interface CreateProfileAction: Action<HandleCreateProfileAction> {

    class ChangeName(private val newName: String) : CreateProfileAction {

        override fun handle(handle: HandleCreateProfileAction) {
            handle.changeName(newName)
        }
    }

    class ChangeSurname(private val newSurname: String) : CreateProfileAction {

        override fun handle(handle: HandleCreateProfileAction) {
            handle.changeSurname(newSurname)
        }
    }

    class AddPhoto(private val newPhoto: Uri) : CreateProfileAction {

        override fun handle(handle: HandleCreateProfileAction) {
            handle.addPhoto(newPhoto)
        }
    }

    class CreateProfile : CreateProfileAction {

        override fun handle(handle: HandleCreateProfileAction) {
            handle.createProfile()
        }
    }
}

