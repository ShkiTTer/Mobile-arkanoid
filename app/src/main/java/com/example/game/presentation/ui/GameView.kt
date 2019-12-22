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
import com.example.game.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.*

class GameView(context: Context, private val viewModel: GameViewModel) : SurfaceView(context),
    SurfaceHolder.Callback {
    companion object {
        private const val DEFAULT_GENERATE_PERIOD = 3000L
    }

    private var gameThread: GameThread?
    private val player = Player(context, context.getDrawable(R.drawable.paddle)!!.toBitmap())
    private val ball = Ball(context.getDrawable(R.drawable.ball)!!.toBitmap())
    private val bricks = mutableListOf<Brick>()

    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    private var generator: Job = Job()
    private var generatorPeriod = DEFAULT_GENERATE_PERIOD
    private val brickCount: Int

    private var isIncreased = false

    private val greenBrick = context.getDrawable(R.drawable.element_green_rectangle)!!.toBitmap()

    init {
        holder.addCallback(this)

        gameThread = GameThread(holder, this)
        setZOrderOnTop(false)
        holder.setFormat(PixelFormat.TRANSPARENT)

        brickCount = screenWidth / (greenBrick.width + Brick.DEFAULT_MARGIN)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        gameThread?.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        gameThread?.stop()
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

    private fun startGenerateBricks(): Job {
        return CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                if (!this.isActive) break

                delay(generatorPeriod)
                createNewBrickLine()
            }

            return@launch
        }
    }

    private fun createNewBrickLine() {
        for (i in 0 until brickCount) {
            val brick: Brick = if (i == 0)
                Brick(greenBrick, 1, 1)
            else
                Brick(greenBrick, 1, 1, bricks.last().right + 20)

            bricks.add(brick)
        }
    }

    fun update() {
        val score = viewModel.score.value ?: 0

        if (score != 0 && score % 10 == 0 && !isIncreased) {
            player.increaseVelocity()
            ball.increaseVelocity()
            bricks.first().increaseVelocity()
            isIncreased = true
        }

        player.update()
        ball.update()

        if (ball.bottom >= screenHeight) {
            gameThread?.stop()
            viewModel.endGame()
        }

        if (ball.bottom >= player.top) {
            if (ball.centerX >= player.left && ball.centerX <= player.right) {
                ball.reverseVelocityY()
            }
        }

        for (brick in bricks) {
            brick.update()

            if (brick.bottom >= screenHeight) {
                gameThread?.stop()
                viewModel.endGame()
            }
            else if (ball.right >= brick.left && ball.left <= brick.right && ball.bottom >= brick.top && ball.top <= brick.bottom) {
                brick.hit()
                isIncreased = false

                if (ball.bottom >= brick.top && ball.top <= brick.bottom) ball.reverseVelocityX()
                if (ball.right >= brick.left && ball.left <= brick.right) ball.reverseVelocityY()

                if (brick.hp == 0) {
                    viewModel.increaseScore(brick.destroyPoints)
                    bricks.remove(brick)
                    break
                }
            }
        }
    }

    fun startGame() {
        gameThread = GameThread(holder, this)
        gameThread?.start()
        viewModel.startGame()
        generator = startGenerateBricks()
    }

    fun stopGame() {
        gameThread?.stop()
        gameThread = null
        GlobalScope.launch(Dispatchers.Main) { generator.cancelAndJoin() }
    }
}