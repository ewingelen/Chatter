package com.ewingelen.chatter.auth.confirmCode.domain

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 07.05.2023.
 */
interface ConfirmCodeInteractor {

    suspend fun auth(
        block: suspend () -> String,
        onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    )

    class Base @Inject constructor(
        private val repository: ConfirmCodeRepository.Save,
        private val errorMapper: ConfirmCodeErrorMapper
    ) : ConfirmCodeInteractor {

        override suspend fun auth(
            block: suspend () -> String,
            onSuccess: () -> Unit,
            onFailure: (error: String) -> Unit
        ) =
            try {
                val id = block.invoke()
                repository.saveUser(id)
                onSuccess()
            } catch (e: Exception) {
                val error = errorMapper.map(e)
                onFailure(error)
            }
    }
}