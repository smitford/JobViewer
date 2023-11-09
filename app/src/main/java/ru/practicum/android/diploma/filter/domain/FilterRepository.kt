package ru.practicum.android.diploma.filter.domain

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

interface FilterRepository {
    fun getFilterSettings(): FilterParameters
    fun saveFilterSettings(filterParameters: FilterParameters)
    fun clearFilterSettings()
    fun saveSalarySettings(salary: String, onlyWithSalary: Boolean)
}