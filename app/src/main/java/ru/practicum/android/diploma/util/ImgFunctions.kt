package ru.practicum.android.diploma.util

import android.util.DisplayMetrics

object ImgFunctions {
    fun roundCorner(densityDpi: Int, rounding: Int): Int =
        rounding * (densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}