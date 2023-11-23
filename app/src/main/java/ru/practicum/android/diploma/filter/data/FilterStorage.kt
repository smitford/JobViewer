package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

interface FilterStorage {
    fun getFilterSettings(): FilterParameters
    fun saveFilterSettings(filterParameters: FilterParameters)
    fun clearFilterSettings()
}