package com.example.game.domain.usecase

import com.example.game.domain.entity.UserResult
import com.example.game.domain.repository.IRepository
import com.example.game.domain.usecase.common.UseCase

class GetResultsUseCase(private val repository: IRepository) : UseCase<List<UserResult>>() {
    override fun doInBackground(): List<UserResult>? {
        return repository.getResults()
    }
}