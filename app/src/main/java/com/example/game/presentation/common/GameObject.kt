package com.example.game.presentation.common

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

abstract class GameObject(private val bitmap: Bitmap) {
    protected var x = 0
    protected var y = 0

    val width: Int = bitmap.width
    val height: Int = bitmap.height

    val left: Int
        get() = x

    val right: Int
        get() = x + width

    val top: Int
        get() = y

    val bottom: Int
        get() = y + height

    val centerX: Int
        get() = x + width / 2

    val centerY: Int
        get() = y + height / 2

    protected val screenWidth = Resources.getSystem().displayMetrics.widthPixels
    protected val screenHeight = Resources.getSystem().displayMetrics.heightPixels

    abstract fun update()

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, x.toFloat(), y.toFloat(), null)
    }
}