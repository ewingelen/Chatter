package com.ewingelen.chatter.auth.confirmCode.domain

interface Auth {

    suspend fun perform(repository: ConfirmCodeRepository)

    class Base(private val isNewUser: Boolean) : Auth {

        override suspend fun perform(repository: ConfirmCodeRepository) {
            if (isNewUser) {
                repository.addUser()
            }
        }
    }
}