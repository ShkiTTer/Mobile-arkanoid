package com.example.game.presentation.objects

import android.graphics.Bitmap
import com.example.game.presentation.common.GameObject

class Brick(bitmap: Bitmap, private var hp: Int, x: Int, y: Int) : GameObject(bitmap) {
    var isAlive = true
        private set

    init {
        this.x = x
        this.y = y
    }

    fun destroy() {
        isAlive = false
    }
}