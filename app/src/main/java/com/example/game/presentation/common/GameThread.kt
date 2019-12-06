package com.example.game.presentation.common

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.example.game.presentation.ui.GameView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class GameThread(private val holder: SurfaceHolder, private val gameView: GameView): CoroutineScope {
    companion object {
        private const val FPS = 60
        private const val TARGET_TIME = 1000L / FPS

        private var canvas: Canvas? = null
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var isRunning = false

    private fun run() {
        launch {
            var startTime: Long
            var duringTime: Long
            var waitTime: Long

            while (isRunning) {
                startTime = System.nanoTime()
                canvas = null

                try {
                    canvas = holder.lockCanvas()

                    synchronized(holder) {
                        gameView.update()
                        gameView.draw(canvas)
                    }
                }
                catch (e: Exception) {
                    e.printStackTrace()
                }
                finally {
                    if (canvas != null) {
                        try {
                            holder.unlockCanvasAndPost(canvas)
                        }
                        catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

                duringTime = (System.nanoTime() - startTime) / 1000000
                waitTime = TARGET_TIME - duringTime

                delay(waitTime)
            }
        }
    }

    fun start() {
        isRunning = true
        run()
    }

    fun stop() {
        isRunning = false
    }
}