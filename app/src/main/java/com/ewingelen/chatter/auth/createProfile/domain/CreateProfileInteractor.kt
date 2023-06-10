package com.ewingelen.chatter.auth.createProfile.domain

import com.ewingelen.chatter.auth.core.domain.AuthorizedStatusRepository
import com.ewingelen.chatter.core.domain.HandleDomainError
import javax.inject.Inject

interface CreateProfileInteractor {

    suspend fun createProfile(userInitial: UserInitial): CreateProfileResult

    class Base @Inject constructor(
        private val createProfileRepository: CreateProfileRepository,
        private val authorizedStatusRepository: AuthorizedStatusRepository.Authorize,
        private val handleError: HandleDomainError<String>
    ) : CreateProfileInteractor {

        override suspend fun createProfile(userInitial: UserInitial) =
            try {
                createProfileRepository.createProfile(userInitial)
                authorizedStatusRepository.authorize()
                CreateProfileResult.Success()
            } catch (e: Exception) {
                val error = handleError.handle(e)
                CreateProfileResult.Fail(error)
            }
    }
}