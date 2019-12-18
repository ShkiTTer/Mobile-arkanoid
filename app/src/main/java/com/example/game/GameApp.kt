package com.example.game

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.game.data.common.GameDatabase
import com.example.game.data.repository.Repository
import com.example.game.domain.repository.IRepository
import com.example.game.domain.usecase.GetResultsUseCase
import com.example.game.domain.usecase.SaveResultUseCase
import com.example.game.presentation.viewmodel.GameViewModel
import com.example.game.presentation.viewmodel.MapViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GameApp: Application() {
    private val koinModule = module {
        single { Room.databaseBuilder(androidContext(), GameDatabase::class.java, "GameDatabase").build() }
        single { Repository(get()) as IRepository }

        single { SaveResultUseCase(get()) }
        single { GetResultsUseCase(get()) }

        viewModel { GameViewModel(get(), this@GameApp) }
        viewModel { MapViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(koinModule)
            androidContext(applicationContext)
        }
    }
}