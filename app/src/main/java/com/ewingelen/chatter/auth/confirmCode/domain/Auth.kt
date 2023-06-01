package com.ewingelen.chatter.auth.confirmCode.domain

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
interface Auth {

    suspend fun save(repository: ConfirmCodeRepository)

    suspend fun checkUserExists(repository: ConfirmCodeRepository): Boolean

    class Base : Auth {

        override suspend fun save(repository: ConfirmCodeRepository) {
            repository.saveUser()
        }

        override suspend fun checkUserExists(repository: ConfirmCodeRepository) =
            repository.checkUserExists()
    }
}