package com.example.game.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.game.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameView = GameView(this)
        setContentView(gameView)
        gameView.startGame()
    }
}
