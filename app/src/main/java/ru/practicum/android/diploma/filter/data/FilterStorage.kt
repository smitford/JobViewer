package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.filter.data.models.FilterParametersDto

interface FilterStorage {
    fun getFilterSettings(): FilterParametersDto
    fun saveFilterSettings(filterParameters: FilterParametersDto)
    fun clearFilterSettings()
    fun saveCountryToFilter(country: CountryDto)
    fun clearCountryInFilter()
    fun getCountry(): CountryDto?
}