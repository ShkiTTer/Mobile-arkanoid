package com.example.game.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.game.R
import com.example.game.databinding.ActivityGameBinding
import com.example.game.presentation.viewmodel.GameViewModel
import kotlinx.android.synthetic.main.activity_game.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameActivity : AppCompatActivity() {
    private val gameViewModel by viewModel<GameViewModel>()
    private lateinit var binding: ActivityGameBinding
    private val gameView: GameView by lazy { GameView(this, gameViewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_game)

        binding.apply {
            lifecycleOwner = this@GameActivity
            score = gameViewModel.score
            isPause = gameViewModel.isPause
            isEnded = gameViewModel.isEnded
        }

        binding.ivPause.setOnClickListener {
            if (gameViewModel.isEnded.value == true) return@setOnClickListener

            if (gameViewModel.isPause.value == true) {
                gameView.startGame()
                gameViewModel.resumeGame()
            }
            else {
                gameView.stopGame()
                gameViewModel.pauseGame()
            }
        }

        binding.btnMenu.setOnClickListener {
            finish()
        }

        btnClose.setOnClickListener {
            finish()
        }

        btnNewGame.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnResume.setOnClickListener {
            gameView.startGame()
            gameViewModel.resumeGame()
        }

        gameView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        binding.container.addView(gameView, 0)
    }

    override fun onResume() {
        super.onResume()
        gameView.startGame()
        gameViewModel.resumeGame()
    }

    override fun onPause() {
        super.onPause()
        gameView.stopGame()
        gameViewModel.pauseGame()
    }
}
