package com.ewingelen.chatter.core.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ewingelen.chatter.auth.createProfile.data.cache.CreateProfileDao
import com.ewingelen.chatter.core.data.cache.model.UserLocal

@Database(entities = [UserLocal::class], version = 1)
abstract class ChatterDatabase : RoomDatabase() {

    abstract fun createProfileDao(): CreateProfileDao
}