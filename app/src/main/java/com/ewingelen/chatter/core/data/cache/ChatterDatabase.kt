package com.ewingelen.chatter.core.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewingelen.chatter.auth.createProfile.data.cache.CreateProfileDao
import com.ewingelen.chatter.core.data.cache.model.ChatLocal
import com.ewingelen.chatter.core.data.cache.model.UserLocal
import com.ewingelen.chatter.createChat.data.cache.CreateChatDao

@Database(entities = [UserLocal::class, ChatLocal::class], version = 1)
abstract class ChatterDatabase : RoomDatabase() {

    abstract fun createProfileDao(): CreateProfileDao

    abstract fun createChatDao(): CreateChatDao
}