package com.ewingelen.chatter.auth.createProfile.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ewingelen.chatter.core.data.cache.model.UserLocal

@Dao
interface CreateProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserLocal)
}