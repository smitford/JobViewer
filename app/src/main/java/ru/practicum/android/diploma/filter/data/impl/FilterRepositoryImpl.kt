package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.filter.data.FilterNetwork
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.data.convertors.AreasConvertor
import ru.practicum.android.diploma.filter.data.convertors.CountryConvertor
import ru.practicum.android.diploma.filter.data.convertors.FilterParametersConvertor
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.Area
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage,
    private val filterNetwork: FilterNetwork
) : FilterRepository {
    override fun getFilterSettings(): FilterParameters {
        return FilterParametersConvertor.filterParamDtoToFilterParam(filterStorage.getFilterSettings())
    }

    override fun saveFilterSettings(filterParameters: FilterParameters) {
        filterStorage.saveFilterSettings(
            FilterParametersConvertor.filterParamToFilterParamDto(filterParameters)
        )
    }

    override fun clearFilterSettings() {
        filterStorage.clearFilterSettings()
    }

    override fun saveSalarySettings(salary: String, onlyWithSalary: Boolean) {
        val filters = getFilterSettings()
        saveFilterSettings(
            filters.copy(
                salary = salary.ifEmpty { null },
                onlyWithSalary = onlyWithSalary
            )
        )
    }

    override suspend fun getCountries(): Flow<DtoConsumer<List<Country>>> {
        return filterNetwork.getCountries()
    }

    override fun saveCountryToFilter(country: Country) {
        filterStorage.saveCountryToFilter(CountryConvertor.countryToCountryDto(country))
    }

    override fun clearCountryInFilter() {
        filterStorage.clearCountryInFilter()
    }

    override suspend fun getAllArea(): Flow<DtoConsumer<List<Area>>> = flow {
        filterNetwork.getAllArea().collect {
            if (it is DtoConsumer.Success) {
                AreasConvertor.convertAreasDtoListToAreaList(it.data)
            }
        }
    }

    suspend fun getAreasById(id: String): Flow<DtoConsumer<Area>> = flow {
        filterNetwork.getAreasById(id).collect {
            if (it is DtoConsumer.Success) {
                AreasConvertor.convertAreasDtoToAreaList(it.data)
            }
        }
    }
}