package com.ewingelen.chatter.auth.confirmCode.domain

import android.util.Log
import com.ewingelen.chatter.auth.core.domain.AuthorizedStatusRepository
import javax.inject.Inject

interface ConfirmCodeInteractor {

    suspend fun confirmCode(block: suspend () -> Unit): ConfirmCodeResult

    class Base @Inject constructor(
        private val authorizedStatusRepository: AuthorizedStatusRepository.Save,
        private val confirmCodeRepository: ConfirmCodeRepository,
        private val errorMapper: ConfirmCodeErrorMapper
    ) : ConfirmCodeInteractor {

        override suspend fun confirmCode(block: suspend () -> Unit) = try {
            block.invoke()
            val userExists = confirmCodeRepository.checkUserExists()
            if (userExists) {
                authorizedStatusRepository.authorize()
            }
            ConfirmCodeResult.Success(userExists)
        } catch (e: Exception) {
            val error = errorMapper.map(e)
            ConfirmCodeResult.Fail(error)
        }
    }
}