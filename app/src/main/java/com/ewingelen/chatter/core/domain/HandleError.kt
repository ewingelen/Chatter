package com.ewingelen.chatter.core.domain


interface HandleError<T> {

    suspend fun handle(block: suspend () -> Unit): T
}