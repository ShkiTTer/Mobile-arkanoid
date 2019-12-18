package com.example.game.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.game.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("app:src")
    fun setSrc(view: ImageView, isPause: Boolean) {
        if (isPause) {
            view.setImageResource(R.drawable.ic_play)
        }
        else {
            view.setImageResource(R.drawable.ic_pause)
        }
    }
}