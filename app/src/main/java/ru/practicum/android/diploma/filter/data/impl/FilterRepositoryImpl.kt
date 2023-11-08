package ru.practicum.android.diploma.filter.data.impl

import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.FilterParameters

class FilterRepositoryImpl(private val filterStorage: FilterStorage) : FilterRepository {
   override fun getFilterSettings(): FilterParameters {
        return filterStorage.getFilterSettings()
    }
   override fun saveFilterSettings(filterParameters: FilterParameters) {
        filterStorage.saveFilterSettings(filterParameters)
    }
}