package com.ewingelen.chatter.core.data.cloud

import java.net.InetAddress
import java.net.UnknownHostException
import javax.inject.Inject

interface CheckInternetConnection {

    fun available(): Boolean

    class Base @Inject constructor() : CheckInternetConnection {

        override fun available() =
            try {
                val address = InetAddress.getByName("www.google.com")
                !address.equals("")
            } catch (e: UnknownHostException) {
                false
            }
    }
}