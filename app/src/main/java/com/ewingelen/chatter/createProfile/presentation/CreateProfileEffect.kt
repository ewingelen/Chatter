package com.ewingelen.chatter.createProfile.presentation

import com.ewingelen.chatter.core.presentation.Effect

interface CreateProfileEffect : Effect<HandleCreateProfileEffect> {

    class ProfileCreated : CreateProfileEffect {

        override fun handle(handle: HandleCreateProfileEffect) {
            handle.profileCreated()
        }
    }
}