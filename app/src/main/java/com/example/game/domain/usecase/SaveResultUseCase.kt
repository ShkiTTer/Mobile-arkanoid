package com.example.game.domain.usecase

import com.example.game.domain.repository.IRepository
import com.example.game.domain.entity.UserResult
import com.example.game.domain.usecase.common.UseCase

class SaveResultUseCase(private val repository: IRepository): UseCase<Unit>() {
    var userResult: UserResult? = null

    override fun doInBackground() {
        val result = userResult ?: return
        return repository.saveResult(result)
    }
}