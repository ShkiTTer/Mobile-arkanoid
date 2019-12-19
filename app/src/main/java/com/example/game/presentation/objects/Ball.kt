package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject
import kotlin.math.absoluteValue

class Ball(bitmap: Bitmap): GameObject(bitmap) {
    companion object {
        private const val VELOCITY = 10
    }

    init {
        reset()
    }

    var xVelocity = VELOCITY
        private set
    var yVelocity = - VELOCITY
        private set

    override fun update() {
        if (x <= 0 || x + width >= screenWidth) reverseVelocityX()
        if (y <= 0) reverseVelocityY()

        x += xVelocity
        y += yVelocity
    }

    fun reverseVelocityX() {
        xVelocity = -xVelocity
    }

    fun reverseVelocityY() {
        yVelocity = -yVelocity
    }

    override fun increaseVelocity() {
        if (xVelocity.absoluteValue < 20) {
            if (xVelocity < 0) xVelocity--
            else xVelocity++
        }

        if (yVelocity.absoluteValue < 20) {
            if (yVelocity < 0)  yVelocity--
            else  yVelocity++
        }
    }

    override fun reset() {
        x = screenWidth / 2
        y = screenHeight / 2
    }
}