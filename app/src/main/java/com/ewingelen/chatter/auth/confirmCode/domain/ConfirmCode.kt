package com.ewingelen.chatter.auth.confirmCode.domain

import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConfirmCode {

    suspend fun confirm(verificationId: String, smsCode: String): Auth

    class Base @Inject constructor() : ConfirmCode {

        override suspend fun confirm(verificationId: String, smsCode: String): Auth {
            val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
            val authResult = Firebase.auth.signInWithCredential(credential).await()
            return Auth.Base(authResult.additionalUserInfo!!.isNewUser)
        }
    }
}