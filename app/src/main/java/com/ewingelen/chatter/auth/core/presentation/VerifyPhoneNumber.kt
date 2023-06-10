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


interface VerifyPhoneNumber {

    fun verify(activity: Activity)

    class Base(
        private val onVerificationStateChanged: OnVerificationStateChanged,
        private val phoneNumber: String
    ) : VerifyPhoneNumber {

        override fun verify(activity: Activity) {
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    onVerificationStateChanged.onVerificationCompleted()
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    onVerificationStateChanged.onVerificationFailed(e)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, token)
                    onVerificationStateChanged.onCodeSent(verificationId)
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