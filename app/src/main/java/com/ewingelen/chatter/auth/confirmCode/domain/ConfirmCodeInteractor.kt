package com.ewingelen.chatter.auth.confirmCode.domain

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeInteractor {

    suspend fun auth(handleAuth: HandleAuth)

    class Base @Inject constructor(
        private val repository: ConfirmCodeRepository,
        private val errorMapper: ConfirmCodeErrorMapper
    ) : ConfirmCodeInteractor {

        override suspend fun auth(handleAuth: HandleAuth) = try {
            val authResult = handleAuth.auth()
            val usersExists = authResult.checkUserExists(repository)
            if (usersExists) {
                authResult.save(repository)
            }
            handleAuth.success(usersExists)
        } catch (e: Exception) {
            val error = errorMapper.map(e)
            handleAuth.failure(error)
        }
    }
}