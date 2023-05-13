package com.ewingelen.chatter.auth.confirmCode.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeRepository {

    interface Save {

        suspend fun saveUser(id: String)
    }

    interface Read {

        suspend fun fetchUserId(): String
    }

    interface Mutable: Save, Read
}