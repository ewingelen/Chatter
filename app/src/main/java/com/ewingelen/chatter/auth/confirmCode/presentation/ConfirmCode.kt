package com.ewingelen.chatter.auth.confirmCode.presentation

import android.util.Log
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

interface ConfirmCode {

    suspend fun confirm(verificationId: String, smsCode: String)

    class Base @Inject constructor() : ConfirmCode {

        override suspend fun confirm(verificationId: String, smsCode: String) {
            val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
            Firebase.auth.signInWithCredential(credential).await()
        }
    }
}