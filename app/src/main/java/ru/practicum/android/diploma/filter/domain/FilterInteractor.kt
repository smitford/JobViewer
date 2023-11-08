package ru.practicum.android.diploma.filter.domain

import ru.practicum.android.diploma.filter.domain.models.FilterParameters

interface FilterInteractor {

    fun getFilterSettings(): FilterParameters
    fun clearFilterSettings()
    fun saveSalarySettings(salary: String, onlyWithSalary: Boolean)
}