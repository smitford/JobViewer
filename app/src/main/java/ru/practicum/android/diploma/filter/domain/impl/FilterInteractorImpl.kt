package ru.practicum.android.diploma.filter.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

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

    override suspend fun getCountry(): Flow<DtoConsumer<List<Country>>> {
        return filterRepository.getCountries()
    }

    override fun saveCountryToFilter(country: Country){
        filterRepository.saveCountryToFilter(country)
    }

    override fun clearCountryInFilter(){
        filterRepository.clearCountryInFilter()
    }

}