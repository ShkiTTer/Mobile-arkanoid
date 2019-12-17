package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject

class Brick(bitmap: Bitmap, val destroyPoints: Int, var hp: Int, x: Int = DEFAULT_X, y: Int = DEFAULT_Y) : GameObject(bitmap) {
    companion object {
        private const val DEFAULT_VELOCITY = 1
        private const val DEFAULT_X = 50
        private const val DEFAULT_Y = 150

        const val DEFAULT_MARGIN = 20
    }

    private var velocity = DEFAULT_VELOCITY

    init {
        this.x = x
        this.y = y
    }

    override fun update() {
        this.y += velocity
    }

    fun hit() {
        hp--
    }

    override fun increaseVelocity() {
        if (velocity < 10) velocity++
    }
}