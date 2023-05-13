package com.ewingelen.chatter.auth.confirmCode.presentation

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 29.04.2023.
 */
interface ConfirmCode {

    suspend fun confirm(verificationId: String, smsCode: String): AuthResult

    class Base @Inject constructor() : ConfirmCode {

        override suspend fun confirm(verificationId: String, smsCode: String): AuthResult =
            suspendCoroutine { continuation ->
                val credential = PhoneAuthProvider.getCredential(verificationId, smsCode)
                Firebase.auth.signInWithCredential(credential).addOnSuccessListener { authResult ->
                    continuation.resume(authResult)
                }.addOnFailureListener { e ->
                    continuation.resumeWithException(e)
                }
            }
    }
}