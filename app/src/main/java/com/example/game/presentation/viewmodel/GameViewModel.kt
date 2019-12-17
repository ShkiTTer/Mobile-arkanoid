package com.example.game.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.game.domain.usecase.SaveResultUseCase

class GameViewModel(
    private val saveResultUseCase: SaveResultUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    companion object {
        private const val DEFAULT_SCORE = 0
    }

    val score = MutableLiveData(DEFAULT_SCORE)
    val isPlaying = MutableLiveData<Boolean>(true)

    fun increaseScore(points: Int) {
        val value = score.value ?: return
        score.value = value + points
    }

    fun stopGame() {
        isPlaying.value = false
    }

    fun startGame() {
        isPlaying.value = true
    }

    fun saveScore() {
        val tempScore = score.value ?: 0


    }
}