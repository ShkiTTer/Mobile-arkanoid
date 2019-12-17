package com.example.game

import android.app.Application
import com.example.game.presentation.viewmodel.GameViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GameApp: Application() {
    private val koinModule = module {
        viewModel { GameViewModel() }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(koinModule)
            androidContext(applicationContext)
        }
    }
}