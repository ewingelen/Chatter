package com.ewingelen.chatter.auth.createProfile.domain

import android.net.Uri

interface UserInitial {

    interface Mapper<T> {

        fun map(name: String, surname: String, photo: Uri): T
    }

    fun <T> map(mapper: Mapper<T>): T

    class Base(
        private val name: String,
        private val surname: String,
        private val photo: Uri
    ) : UserInitial {

        override fun <T> map(mapper: Mapper<T>) =
            mapper.map(name = name, surname = surname, photo = photo)
    }
}