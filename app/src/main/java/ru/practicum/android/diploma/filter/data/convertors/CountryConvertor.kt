package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.AreaDto
import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.filter.domain.models.Country

object CountryConvertor {
    fun countryToCountryDto(country: Country) : CountryDto {
        return CountryDto(
            id = country.id,
            name = country.name
        )
    }

    private fun areasDtoToCounty(areaDto: AreaDto) : Country {
        return Country(
            id = areaDto.id,
            name = areaDto.name
        )
    }

    fun areasDtoListToCountry(areaDtoList: List<AreaDto>): List<Country> {
        val countryList = areaDtoList.map { areaDto -> areasDtoToCounty(areaDto) }
        val convertedList = countryList.toMutableList()
        val countryToMove = convertedList.find { it.id == "1001" }
        convertedList.remove(countryToMove)
        if (countryToMove != null) {
            convertedList.add(countryToMove)
        }
        return convertedList
    }
}