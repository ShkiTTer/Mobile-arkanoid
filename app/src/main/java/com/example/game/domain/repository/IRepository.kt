package com.example.game.domain.repository

import com.example.game.domain.entity.UserResult

interface IRepository {
    fun saveResult(userResult: UserResult)
    fun getResults(): List<UserResult>
}