package com.ewingelen.chatter.createProfile.presentation

import android.net.Uri
import com.ewingelen.chatter.core.presentation.Action

interface CreateProfileAction : Action<HandleCreateProfileAction> {

    class ChangeName(private val newName: String) : CreateProfileAction {

        override fun handle(handle: HandleCreateProfileAction) {
            handle.changeName(newName)
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

