package com.nikolaykul.recyclerviewitemexample.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.cardview.widget.CardView
import com.nikolaykul.recyclerviewitemexample.databinding.WidgetColorLoadingViewBinding
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ColorLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding = WidgetColorLoadingViewBinding.inflate(LayoutInflater.from(context), this)
    private val loadingRunnable = Runnable { setState(LoadingState.FINISHED) }

    init {
        setBackgroundColor(Color.TRANSPARENT)
    }

    fun cancelLoading() {
        setState(LoadingState.CANCELED)
    }

    fun loadColor(@ColorInt newColor: Int) {
        setState(
            LoadingState.STARTED(newColor, getLoadingTime())
        )
    }

    private fun getLoadingTime(): Long {
        val extraSeconds = Random(System.nanoTime()).nextLong(2L) + 1L    // [1, 3)
        return TimeUnit.SECONDS.toMillis(extraSeconds)
    }

    private fun setState(newState: LoadingState) {
        when (newState) {
            is LoadingState.STARTED -> {
                removeCallbacks(loadingRunnable)
                postDelayed(loadingRunnable, newState.delay)
                binding.run {
                    colorView.run {
                        setBackgroundColor(newState.color)
                        visibility = View.INVISIBLE
                    }
                    progressBar.visibility = View.VISIBLE
                }
            }
            LoadingState.FINISHED -> {
                binding.run {
                    colorView.visibility = View.VISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            }
            LoadingState.CANCELED -> {
                removeCallbacks(loadingRunnable)
                binding.run {
                    colorView.visibility = View.INVISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            }
        }
    }

    private sealed class LoadingState {
        data class STARTED(@ColorInt val color: Int, val delay: Long) : LoadingState()

        object FINISHED : LoadingState()

        object CANCELED : LoadingState()
    }
}