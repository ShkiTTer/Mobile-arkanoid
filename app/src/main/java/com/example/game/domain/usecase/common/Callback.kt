package com.example.game.domain.usecase.common

interface Callback<T> {
    fun onComplete(result: T?)
    fun onFailure(t: Throwable)
}