package com.ewingelen.chatter.auth.confirmCode.domain

interface ConfirmCodeErrorMapper {

    fun map(e: Exception): String
}