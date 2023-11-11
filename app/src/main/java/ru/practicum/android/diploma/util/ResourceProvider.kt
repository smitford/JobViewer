package ru.practicum.android.diploma.util

interface ResourceProvider {
    fun getString(resId: Int): String
}