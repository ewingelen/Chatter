package com.ewingelen.chatter.profile.presentation.contract

interface ProfileAction {

    fun handle(handle: HandleProfileAction)

    class SignOut : ProfileAction {

        override fun handle(handle: HandleProfileAction) {
            handle.signOut()
        }
    }
}