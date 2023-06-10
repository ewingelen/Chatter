package com.ewingelen.chatter.core.domain

interface HandleDomainError<T> {

    fun handle(e: Exception): T
}