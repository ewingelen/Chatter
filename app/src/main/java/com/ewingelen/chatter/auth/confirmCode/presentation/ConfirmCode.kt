package com.ewingelen.chatter.auth.confirmCode.presentation

import com.ewingelen.chatter.auth.confirmCode.domain.Auth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
interface ConfirmCode {

    suspend fun confirm(verificationId: String, smsCode: String): Auth

    class Base @Inject constructor() : ConfirmCode {

        override suspend fun confirm(verificationId: String, smsCode: String): Auth {
            val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
            val authResult = Firebase.auth.signInWithCredential(credential).await()
            return Auth.Base()
        }
    }
}