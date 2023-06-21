package com.ewingelen.chatter.auth.core.domain

interface AuthorizedStatusRepository {

    interface Save {

        suspend fun authorize()
    }

    interface Read {

        suspend fun checkUserAuthorized(): Boolean
    }
}