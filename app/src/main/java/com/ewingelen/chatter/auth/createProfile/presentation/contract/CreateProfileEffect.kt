package com.ewingelen.chatter.auth.createProfile.presentation.contract

import com.ewingelen.chatter.core.presentation.Effect

interface CreateProfileEffect : Effect<HandleCreateProfileEffect> {

    class ProfileCreated : CreateProfileEffect {

        override fun handle(handle: HandleCreateProfileEffect) {
            handle.profileCreated()
        }
    }
}