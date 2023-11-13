package ru.practicum.android.diploma.filter.data.convertors

import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.filter.domain.models.Country

object CountryConvertor {
    fun countryDtoToCountry(country: CountryDto) : Country {
        return Country(
            id = country.id,
            name = country.name
        )
    }
}