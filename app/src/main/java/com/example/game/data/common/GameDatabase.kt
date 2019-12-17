package com.example.game.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.game.data.dao.UserResultDao
import com.example.game.data.entity.UserResult

@Database(entities = [UserResult::class], version = 1)
abstract class GameDatabase: RoomDatabase() {
    abstract fun getUserResultDao(): UserResultDao
}