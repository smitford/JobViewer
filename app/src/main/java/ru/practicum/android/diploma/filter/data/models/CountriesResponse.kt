package ru.practicum.android.diploma.filter.data.models

import ru.practicum.android.diploma.search.data.models.ResponseDto

data class CountriesResponse(
    val results: List<CountryDto>
) : ResponseDto()
