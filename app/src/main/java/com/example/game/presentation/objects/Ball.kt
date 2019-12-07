package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject

class Ball(bitmap: Bitmap): GameObject(bitmap), IMovable {
    companion object {
        private const val VELOCITY = 10
    }

    init {
        x = screenWidth / 2
        y = screenHeight / 2
    }

    var xVelocity = VELOCITY
        private set
    var yVelocity = - VELOCITY
        private set

    override fun update() {
        if (x <= 0 || x + width >= screenWidth) reverceVelocityX()
        if (y <= 0) reverceVelocityY()

        x += xVelocity
        y += yVelocity
    }

    fun reverceVelocityX() {
        xVelocity = -xVelocity
    }

    fun reverceVelocityY() {
        yVelocity = -yVelocity
    }
}