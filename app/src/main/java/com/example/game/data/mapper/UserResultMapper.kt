package com.example.game.data.mapper

import com.example.game.domain.entity.UserResult

object UserResultMapper {
    fun toDb(userResult: UserResult): com.example.game.data.entity.UserResult =
        com.example.game.data.entity.UserResult(
            userResult.score,
            userResult.latitude,
            userResult.longitude
        )

    fun fromDb(userResult: com.example.game.data.entity.UserResult): UserResult =
        UserResult(userResult.score, userResult.latitude, userResult.longitude)
}