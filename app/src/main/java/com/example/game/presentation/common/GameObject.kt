package com.example.game.presentation.common

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

abstract class GameObject(private val bitmap: Bitmap) {
    var x = 0
    var y = 0
    var width = 0
    var height = 0
    private val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    private val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    init {
        width = bitmap.width
        height = bitmap.height
    }

    abstract fun update()

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }
}