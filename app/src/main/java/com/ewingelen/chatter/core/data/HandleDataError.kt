package com.ewingelen.chatter.core.data

interface HandleDataError {

    suspend fun <T> handle(block: suspend () -> T): T
}