package com.ewingelen.chatter.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

//TODO: layer
interface ObserveInternetConnection {

    fun observe(): Flow<Boolean>

    class Base @Inject constructor(
        @ApplicationContext private val context: Context
    ) : ObserveInternetConnection {

        override fun observe() = callbackFlow {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            val networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(true) }
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    launch { send(false) }
                }
            }

            if (
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                connectivityManager.activeNetwork == null ||
                connectivityManager.activeNetworkInfo == null
            ) {
                launch { send(false) }
            }

            connectivityManager.registerNetworkCallback(request, networkCallback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }.distinctUntilChanged()
    }
}