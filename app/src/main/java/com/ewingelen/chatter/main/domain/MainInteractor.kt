package com.ewingelen.chatter.main.domain

import javax.inject.Inject

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 13.05.2023.
 */
interface MainInteractor {

    suspend fun checkUserAuthorized(): Boolean

    class Base @Inject constructor(
        private val repository: MainRepository
    ) : MainInteractor {

        override suspend fun checkUserAuthorized() = repository.checkUserAuthorized()
    }
}