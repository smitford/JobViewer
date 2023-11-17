package ru.practicum.android.diploma.filter.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface FilterRepository {
    fun getFilterSettings(): FilterParameters
    fun saveFilterSettings(filterParameters: FilterParameters)
    fun clearFilterSettings()
    fun saveSalarySettings(salary: String, onlyWithSalary: Boolean)
    suspend fun getCountries(): Flow<DtoConsumer<List<Country>>>
    fun saveCountryToFilter(country: Country)
    fun clearCountryInFilter()
    suspend fun getRegions(): Flow<DtoConsumer<List<Region>>>
    fun saveRegionToFilter(region: Region)
    fun deleteRegionFromFilter()
}