package com.ewingelen.chatter.auth.confirmCode.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeRepository {

    suspend fun saveUser()

    suspend fun checkUserExists(): Boolean
}