package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject

class Player(bitmap: Bitmap): GameObject(bitmap), IMovable {
    companion object {
        private const val BOTTOM_MARGIN = 20
        private const val VELOCITY = 10
    }

    init {
        x = screenWidth / 2
        y = screenHeight - height - BOTTOM_MARGIN
    }

    override fun update() {
        x += VELOCITY
    }
}