package ru.practicum.android.diploma.util

import android.content.Context

class ResourceProviderImpl(private val context: Context) : ResourceProvider {
    override fun getString(resId: Int) = context.getString(resId)
}