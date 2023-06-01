package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface ProvideUserId {

    fun provide(): String

    class Base : ProvideUserId {

        override fun provide() = Firebase.auth.uid!!
    }
}