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

    private var xVelocity = VELOCITY
    private var yVelocity = - VELOCITY

    override fun update() {
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