package com.example.game.presentation.ui

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.PorterDuff
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.graphics.drawable.toBitmap
import com.example.game.R
import com.example.game.presentation.common.GameThread
import com.example.game.presentation.objects.Ball
import com.example.game.presentation.objects.Brick
import com.example.game.presentation.objects.Player

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private val gameThread: GameThread
    private val player = Player(context, context.getDrawable(R.drawable.paddle)!!.toBitmap())
    private val ball = Ball(context.getDrawable(R.drawable.ball)!!.toBitmap())
    private val bricks = mutableListOf<Brick>()

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        holder.addCallback(this)

        gameThread = GameThread(holder, this)
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        gameThread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        gameThread.stop()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas ?: return

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

        player.draw(canvas)
        ball.draw(canvas)

        bricks.forEach {
            it.draw(canvas)
        }
    }

    fun update() {
        player.update()
        ball.update()
    }

    fun startGame() {
        gameThread.start()
    }

    fun stopGame() {
        gameThread.stop()
    }
}