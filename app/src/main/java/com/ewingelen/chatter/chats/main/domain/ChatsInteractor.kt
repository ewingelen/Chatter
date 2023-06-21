package com.ewingelen.chatter.chats.main.domain

import com.ewingelen.chatter.R
import com.ewingelen.chatter.core.ObserveInternetConnection
import com.ewingelen.chatter.core.domain.ProvideResources
import com.ewingelen.chatter.core.domain.model.Chat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

interface ChatsInteractor {

    suspend fun fetchTopBarTitle(): Flow<String>

    fun fetchChats(): Flow<List<Chat>>

    fun deleteChat(id: String)

    class Base @Inject constructor(
        private val repository: ChatsRepository,
        private val observeInternetConnection: ObserveInternetConnection,
        private val provideResources: ProvideResources,
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

        override fun fetchChats() = repository.fetchChats()

        override fun deleteChat(id: String) = repository.deleteChat(id)
    }
}