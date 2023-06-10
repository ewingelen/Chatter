package com.ewingelen.chatter.auth.confirmCode.domain

interface ConfirmCodeRepository {

    suspend fun checkUserExists(): Boolean
}