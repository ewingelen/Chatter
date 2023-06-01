package com.ewingelen.chatter.createChat.data.local

import androidx.room.Dao
import androidx.room.Insert
import com.ewingelen.chatter.core.data.local.model.ChatLocal

/**
 * Created by Artem Skorik email(skorikartem.work@gmail.com) on 17.05.2023.
 */
@Dao
interface CreateChatDao {

    @Insert
    suspend fun addChat(chatLocal: ChatLocal)
}