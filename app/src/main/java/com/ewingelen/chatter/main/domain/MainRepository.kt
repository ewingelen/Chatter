package com.ewingelen.chatter.main.domain

interface MainRepository {

    suspend fun checkUserAuthorized(): Boolean
}