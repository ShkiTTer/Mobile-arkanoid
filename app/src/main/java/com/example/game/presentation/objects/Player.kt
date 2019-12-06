package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject

class Player(bitmap: Bitmap): GameObject(bitmap) {
    companion object {
        private const val BOTTOM_MARGIN = 20
        private const val VELOCITY = 10
    }

    private var xVelocity = VELOCITY
    private var yVelocity = -VELOCITY

    init {
        x = screenWidth / 2
        y = screenHeight - height - BOTTOM_MARGIN
    }

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun reverceVelocityX() {
        xVelocity = -xVelocity
    }

    fun reverceVelocityY() {
        yVelocity = -yVelocity
    }
}