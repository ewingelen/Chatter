package com.ewingelen.chatter.core.data.cloud.model

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface ProvideUserId {

    fun provide(): String

    class Base @Inject constructor(): ProvideUserId {

        override fun provide() = Firebase.auth.uid!!
    }
}