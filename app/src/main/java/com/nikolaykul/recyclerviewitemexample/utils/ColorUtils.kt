package com.nikolaykul.recyclerviewitemexample.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import kotlin.random.Random

object ColorUtils {
    private const val MAX_RGB = 0xFF

    @ColorInt
    fun random(): Int = random(System.nanoTime())

    @ColorInt
    fun random(seed: Long): Int {
        return with(Random(seed)) {
            Color.rgb(nextInt(MAX_RGB), nextInt(MAX_RGB), nextInt(MAX_RGB))
        }
    }
}
