package com.example.game.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.game.domain.entity.UserResult
import com.example.game.domain.usecase.GetResultsUseCase
import com.example.game.domain.usecase.common.Callback

class MapViewModel(private val getResultsUseCase: GetResultsUseCase): ViewModel() {
    val results = MutableLiveData<List<UserResult>>()

    fun getResults() {
        getResultsUseCase.execute(object : Callback<List<UserResult>> {
            override fun onComplete(result: List<UserResult>?) {
                results.value = result
            }

            override fun onFailure(t: Throwable) {
                t.printStackTrace()
                results.value = null
            }
        })
    }
}