package com.ewingelen.chatter.main.domain

import com.ewingelen.chatter.auth.confirmCode.domain.ConfirmCodeRepository
import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 13.05.2023.
 */
interface MainInteractor {

    suspend fun userAuthorized(): Boolean

    class Base @Inject constructor(
        private val repository: ConfirmCodeRepository.Read
    ) : MainInteractor {

        override suspend fun userAuthorized() = repository.fetchUserId().isNotEmpty()
    }
}