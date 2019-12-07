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
    //private val brickRows: List<List<Int>>
    //private val brickCols: List<Int>

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        holder.addCallback(this)

        gameThread = GameThread(holder, this)
        setZOrderOnTop(true)
        holder.setFormat(PixelFormat.TRANSPARENT)

        val brickImage = context.getDrawable(R.drawable.element_green_rectangle)!!.toBitmap()

        val brick = Brick(brickImage, 1, screenWidth / 2, 100)
        bricks.add(brick)
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

        if (ball.bottom >= screenHeight) {
            gameThread.stop()
        }

        if (ball.bottom >= player.top) {
            if (ball.centerX >= player.left && ball.centerX <= player.right) {
                ball.reverceVelocityY()
            }
        }

        for (brick in bricks) {
            if (ball.right >= brick.left && ball.left <= brick.right && ball.top >= brick.top && ball.bottom <= brick.bottom) {
                brick.destroy()

                if (ball.top >= brick.top && ball.bottom <= brick.bottom) ball.reverceVelocityX()
                if (ball.left >= brick.left && ball.right <= brick.right) ball.reverceVelocityY()
            }
        }
    }

    fun startGame() {
        gameThread.start()
    }

    fun stopGame() {
        gameThread.stop()
    }
}