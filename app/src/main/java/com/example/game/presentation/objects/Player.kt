package com.example.game.presentation.objects

import android.content.Context
import android.graphics.Bitmap
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.example.game.presentation.common.GameObject
import kotlin.math.absoluteValue

class Player(private val context: Context, bitmap: Bitmap) : GameObject(bitmap), SensorEventListener {
    companion object {
        private const val BOTTOM_MARGIN = 20
        private const val VELOCITY = 5
    }

    private val sensor: Sensor
    private var xVelocity = 0

    init {
        x = screenWidth / 2
        y = screenHeight - height - BOTTOM_MARGIN

        val manager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event ?: return

        val speed = event.values[2]

        if (xVelocity.absoluteValue < 20) {
            xVelocity += -(VELOCITY * speed).toInt()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun update() {
        if (x < 0 || x + width > screenWidth) xVelocity = 0

        if (x < 0) x = 0
        if (x + width > screenWidth) x = screenWidth - width

        x += xVelocity
    }
}