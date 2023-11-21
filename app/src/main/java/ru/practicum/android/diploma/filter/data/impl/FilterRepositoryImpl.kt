package ru.practicum.android.diploma.filter.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.filter.data.FilterNetwork
import ru.practicum.android.diploma.filter.data.FilterStorage
import ru.practicum.android.diploma.filter.data.convertors.RegionConvertor
import ru.practicum.android.diploma.filter.data.convertors.CountryConvertor
import ru.practicum.android.diploma.filter.data.convertors.FilterParametersConvertor
import ru.practicum.android.diploma.filter.data.convertors.IndustryConvertor
import ru.practicum.android.diploma.filter.domain.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.FilterParameters
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.DataUtils.Companion.NO_RESULTS_CODE

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

    private suspend fun getAllArea(): Flow<DtoConsumer<List<Region>>> = flow {
        filterNetwork.getAllArea().collect { consumer ->
            if (consumer is DtoConsumer.Success) {
                emit(DtoConsumer.Success(RegionConvertor.convertAreasDtoListToAreaList(consumer.data)))
            } else emit(consumer as DtoConsumer<List<Region>>)
        }
    }

    private suspend fun getAreasById(id: String): Flow<DtoConsumer<List<Region>>> = flow {
        filterNetwork.getAreasById(id).collect { consumer ->
            if (consumer is DtoConsumer.Success) {
                emit(DtoConsumer.Success(RegionConvertor.convertAreasDtoToAreaList(consumer.data)))
            } else emit(consumer as DtoConsumer<List<Region>>)
        }
    }

    override suspend fun getRegions(): Flow<DtoConsumer<List<Region>>> {
        val country = filterStorage.getCountry()
        return if (country != null) {
            getAreasById(country.id)
        } else {
            getAllArea()
        }
    }

    override fun saveRegionToFilter(region: Region) {
        filterStorage.saveRegionToFilter(RegionConvertor.convertRegionToRegionDto(region))
    }

    override fun deleteRegionFromFilter() {
        filterStorage.deleteRegionFromFilter()
    }

    private fun searchRegionInListByName(list: List<Region>, name: String): List<Region> {
        return list.filter { it.name.contains(name, ignoreCase = true) }
    }

    override fun getAreasByName(name: String): Flow<DtoConsumer<List<Region>>> = flow {
        getRegions().collect { consumer ->
            when (consumer) {
                is DtoConsumer.Success -> {
                    val list = searchRegionInListByName(consumer.data, name)
                    if (list.isEmpty()) {
                        emit(DtoConsumer.Error(NO_RESULTS_CODE))
                    } else {
                        emit(DtoConsumer.Success(list))
                    }
                }

                else -> emit(consumer)
            }
        }
    }

    override fun deleteIndustryFromFilter() {
        filterStorage.deleteIndustryFromFilter()
    }

    override fun saveIndustryToFilter(industry: Industry) {
        filterStorage.saveIndustryToFilter(IndustryConvertor.industryToIndustrySp(industry))
    }

    override fun getIndustries(): Flow<DtoConsumer<List<Industry>>> = flow {
        filterNetwork.getIndustries().collect { consumer ->
            if (consumer is DtoConsumer.Success) {
                emit(DtoConsumer.Success(IndustryConvertor.industryDtoListToIndustryList(consumer.data)))
            } else emit(consumer as DtoConsumer<List<Industry>>)
        }
    }
}