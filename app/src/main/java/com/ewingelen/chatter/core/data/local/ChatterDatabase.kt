package com.ewingelen.chatter.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewingelen.chatter.core.data.local.model.ChatLocal
import com.ewingelen.chatter.createChat.data.local.CreateChatDao


@Database(entities = [ChatLocal::class], version = 1)
abstract class ChatterDatabase : RoomDatabase() {

    abstract fun createChatDao(): CreateChatDao
}