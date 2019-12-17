package com.example.game.data.repository

import com.example.game.data.common.GameDatabase
import com.example.game.data.mapper.UserResultMapper
import com.example.game.domain.entity.UserResult
import com.example.game.domain.repository.IRepository

class Repository(private val db: GameDatabase) : IRepository {
    override fun saveResult(userResult: UserResult) {
        db.getUserResultDao().saveResult(UserResultMapper.toDb(userResult))
    }

    override fun getResults(): List<UserResult> {
        return db.getUserResultDao().getResults().map {
            UserResultMapper.fromDb(it)
        }
    }
}