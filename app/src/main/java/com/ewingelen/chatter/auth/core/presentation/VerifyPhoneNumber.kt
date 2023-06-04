package com.ewingelen.chatter.auth.core.presentation

import android.app.Activity
import com.ewingelen.chatter.BuildConfig
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


interface VerifyPhoneNumber {

    suspend fun verify(activity: Activity): VerifyPhoneNumberResult

    class Base(private val phoneNumber: String) : VerifyPhoneNumber {

        override suspend fun verify(activity: Activity) = suspendCoroutine { continuation ->
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    continuation.resume(VerifyPhoneNumberResult.Completed())
//                    onVerificationStateChanged.onVerificationCompleted()
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    continuation.resume(VerifyPhoneNumberResult.Failed(e))
//                    onVerificationStateChanged.onVerificationFailed(e)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, token)
                    continuation.resume(VerifyPhoneNumberResult.CodeSent(verificationId))
//                    onVerificationStateChanged.onCodeSent(verificationId)
                }
            }

            val auth = Firebase.auth.apply {
                useAppLanguage()
                firebaseAuthSettings.setAppVerificationDisabledForTesting(BuildConfig.DEBUG)
            }
            val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
        }
    }
}

interface VerifyPhoneNumberResult {

    interface Mapper {

        fun mapCompleted()

        fun mapFailed(error: Exception)

        fun mapCodeSent(verificationId: String)
    }

    fun map(mapper: Mapper)

    class Completed : VerifyPhoneNumberResult {

        override fun map(mapper: Mapper) = mapper.mapCompleted()
    }

    class Failed(private val error: Exception) : VerifyPhoneNumberResult {

        override fun map(mapper: Mapper) = mapper.mapFailed(error)
    }

    class CodeSent(private val verificationId: String) : VerifyPhoneNumberResult {

        override fun map(mapper: Mapper) = mapper.mapCodeSent(verificationId)
    }
}