package com.example.game.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    companion object {
        private const val DEFAULT_SCORE = 0
        private const val DEFAULT_HP = 3
    }

    val score = MutableLiveData(DEFAULT_SCORE)
    val playerHp = MutableLiveData(DEFAULT_HP)

    fun increaseScore(points: Int) {
        val value = score.value ?: return
        score.value = value + points
    }
}