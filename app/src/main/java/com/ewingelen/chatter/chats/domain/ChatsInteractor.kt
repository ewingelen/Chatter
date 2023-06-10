package com.ewingelen.chatter.chats.domain

import com.ewingelen.chatter.R
import com.ewingelen.chatter.chats.presentation.ChatUi
import com.ewingelen.chatter.core.ObserveInternetConnection
import com.ewingelen.chatter.core.domain.ProvideResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface ChatsInteractor {

    suspend fun fetchTopBarTitle(): Flow<String>

    fun fetchChats(): List<ChatUi>

    class Base @Inject constructor(
        private val observeInternetConnection: ObserveInternetConnection,
        private val provideResources: ProvideResources
    ) : ChatsInteractor {

        override suspend fun fetchTopBarTitle() = callbackFlow {
            val appNameTitle = provideResources.string(R.string.app_name)
            val waitingForInternetConnectionTitle =
                provideResources.string(R.string.error_waiting_internet_connection)
            observeInternetConnection.observe().collect { available ->
                val newTitle = if (available) appNameTitle else waitingForInternetConnectionTitle
                send(newTitle)
            }
        }

        override fun fetchChats(): List<ChatUi> {
            return listOf(
                ChatUi(
                    userId = "r134314fqrqe",
                    userAvatar = 0,
                    userName = "Rebeca Donelli",
                    lastMessage = "Pls take a look at the image I sent",
                    time = "16:04",
                    unreadMessagesCount = 2
                ),
            )
        }
    }
}