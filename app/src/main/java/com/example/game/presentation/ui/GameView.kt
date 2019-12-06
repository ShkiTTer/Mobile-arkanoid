package com.example.game.presentation.ui

import android.content.Context
import android.graphics.Canvas
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.drawable.toBitmap
import com.example.game.R
import com.example.game.presentation.common.GameThread
import com.example.game.presentation.objects.Player

class GameView(context: Context): SurfaceView(context), SurfaceHolder.Callback {
    private val gameThread: GameThread
    private val player = Player(context.getDrawable(R.drawable.paddle)!!.toBitmap())

    init {
        holder.addCallback(this)

        gameThread = GameThread(holder, this)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        gameThread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        gameThread.stop()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    fun update() {

    }
}