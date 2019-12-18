package com.example.game.presentation.viewmodel

import android.app.Application
import android.location.Location
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.game.domain.entity.UserResult
import com.example.game.domain.usecase.SaveResultUseCase
import io.nlopez.smartlocation.SmartLocation

class GameViewModel(
    private val saveResultUseCase: SaveResultUseCase,
    private val app: Application
) : AndroidViewModel(app) {
    companion object {
        private const val DEFAULT_SCORE = 0
    }

    private var location: Location? = null

    val score = MutableLiveData(DEFAULT_SCORE)
    val isPause = MutableLiveData<Boolean>(false)
    val isEnded = MutableLiveData<Boolean>(false)

    init {
        SmartLocation.with(app).location().oneFix().start {
            location = it
        }
    }

    fun increaseScore(points: Int) {
        val value = score.value ?: return
        score.value = value + points
    }

    fun pauseGame() {
        isPause.value = true
    }

    fun resumeGame() {
        isPause.value = false
    }

    fun endGame() {
        isEnded.value = true
        saveScore()
    }

    fun startGame() {
        score.value = 0
        isPause.value = false
        isEnded.value = false
    }

    private fun saveScore() {
        val tempScore = score.value ?: 0
        val tempLocation = location ?: return

        val result =  UserResult(tempScore, tempLocation.latitude, tempLocation.longitude)

        saveResultUseCase.apply {
            userResult = result
            execute()
        }
    }
}