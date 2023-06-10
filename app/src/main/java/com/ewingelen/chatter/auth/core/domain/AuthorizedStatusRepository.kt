package com.ewingelen.chatter.auth.core.domain

interface AuthorizedStatusRepository {

    interface Authorize {

        suspend fun authorize()
    }

    interface Check {

        suspend fun checkUserAuthorized(): Boolean
    }
}