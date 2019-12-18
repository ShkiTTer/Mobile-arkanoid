package com.example.game.domain.usecase.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class UseCase<T>: CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val bgContext = Dispatchers.IO

    abstract fun doInBackground(): T?

    fun execute(callback: Callback<T>? = null) {
        launch {
            try {
                val data = async(bgContext) { doInBackground() }.await()

                callback?.onComplete(data)
            }
            catch (t: Throwable) {
                callback?.onFailure(t)
            }
        }
    }
}