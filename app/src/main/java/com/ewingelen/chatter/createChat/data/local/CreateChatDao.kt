package com.ewingelen.chatter.createChat.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.ewingelen.chatter.core.data.local.model.ChatLocal


@Dao
interface CreateChatDao {

    @Insert
    suspend fun addChat(chatLocal: ChatLocal)
}