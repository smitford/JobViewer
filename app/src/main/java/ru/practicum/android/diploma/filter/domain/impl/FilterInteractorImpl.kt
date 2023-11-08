package ru.practicum.android.diploma.filter.domain.impl

import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.FilterParameters

class FilterInteractorImpl(private val filterRepository: FilterRepository) : FilterInteractor {
    override fun getFilterSettings(): FilterParameters {
        return filterRepository.getFilterSettings()
    }

    override fun clearFilterSettings() {
        return filterRepository.clearFilterSettings()
    }

    override fun saveSalarySettings(salary: String, onlyWithSalary: Boolean) {
        filterRepository.saveSalarySettings(salary, onlyWithSalary)
    }
}