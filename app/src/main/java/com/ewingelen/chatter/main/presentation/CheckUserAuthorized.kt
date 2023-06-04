package com.ewingelen.chatter.main.presentation

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

interface CheckUserAuthorized {

    fun check(): Boolean

    class Base @Inject constructor() : CheckUserAuthorized {

        override fun check() = Firebase.auth.currentUser != null
    }
}