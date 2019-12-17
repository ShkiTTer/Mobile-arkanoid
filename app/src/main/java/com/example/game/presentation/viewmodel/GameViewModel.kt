package com.example.game.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {
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
}