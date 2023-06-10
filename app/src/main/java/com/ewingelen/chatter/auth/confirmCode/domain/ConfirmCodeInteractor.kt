package com.ewingelen.chatter.auth.confirmCode.domain

import javax.inject.Inject

interface ConfirmCodeInteractor {

    suspend fun confirmCode(block: suspend () -> Unit): ConfirmCodeResult

    class Base @Inject constructor(
        private val confirmCodeRepository: ConfirmCodeRepository,
        private val errorMapper: ConfirmCodeErrorMapper
    ) : ConfirmCodeInteractor {

        override suspend fun confirmCode(block: suspend () -> Unit) = try {
            block.invoke()
            val userExists = confirmCodeRepository.checkUserExists()
            ConfirmCodeResult.Success(userExists)
        } catch (e: Exception) {
            val error = errorMapper.map(e)
            ConfirmCodeResult.Fail(error)
        }
    }
}