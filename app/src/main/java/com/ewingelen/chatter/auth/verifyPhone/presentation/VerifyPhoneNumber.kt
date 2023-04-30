package com.ewingelen.chatter.auth.verifyPhone.presentation

import android.app.Activity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 28.04.2023.
 */
interface VerifyPhoneNumber {

    fun verify(activity: Activity)

    class Base(
        private val phoneNumber: String,
        private val onVerificationStateChanged: OnVerificationStateChanged,
    ) : VerifyPhoneNumber {

        override fun verify(activity: Activity) {
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    onVerificationStateChanged.onVerificationCompleted()
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    val errorMapper = VerificationErrorMapper.Base(activity)
                    val error = errorMapper.map(e)
                    onVerificationStateChanged.onVerificationFailed(error)
                }

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationId, token)
                    onVerificationStateChanged.onCodeSent(verificationId)
                }
            }

            val fullPhoneNumber = "+$phoneNumber"
            val auth = Firebase.auth.apply {
                useAppLanguage()
            }
            val phoneAuthOptions = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(fullPhoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
        }
    }
}