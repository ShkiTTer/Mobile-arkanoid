package com.example.game.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserResult(
    val score: Int,
    val latitude: Double,
    val longitude: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)