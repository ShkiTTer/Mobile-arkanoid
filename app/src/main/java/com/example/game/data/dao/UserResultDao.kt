package com.example.game.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.game.data.entity.UserResult

@Dao
interface UserResultDao {
    @Insert
    fun saveResult(userResult: UserResult)

    @Query("Select * From UserResult")
    fun getResults(): List<UserResult>
}