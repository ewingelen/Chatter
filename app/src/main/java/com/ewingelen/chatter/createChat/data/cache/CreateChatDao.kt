package com.ewingelen.chatter.createChat.data.cache

import androidx.room.Dao
import androidx.room.Insert
import com.ewingelen.chatter.core.data.cache.model.ChatLocal


@Dao
interface CreateChatDao {

    @Insert
    suspend fun addChat(chatLocal: ChatLocal)
}