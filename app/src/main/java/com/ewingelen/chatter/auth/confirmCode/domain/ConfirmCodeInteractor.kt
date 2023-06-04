package com.ewingelen.chatter.auth.confirmCode.domain

import javax.inject.Inject

interface ConfirmCodeInteractor {

    suspend fun confirmCode(block: suspend () -> Auth): ConfirmCodeResult

    class Base @Inject constructor(
        private val repository: ConfirmCodeRepository,
        private val errorMapper: ConfirmCodeErrorMapper
    ) : ConfirmCodeInteractor {

        override suspend fun confirmCode(block: suspend () -> Auth) = try {
            val auth = block.invoke()
            auth.perform(repository)
            ConfirmCodeResult.Success(newUser = true)
        } catch (e: Exception) {
            val error = errorMapper.map(e)
            ConfirmCodeResult.Fail(error)
        }
    }
}