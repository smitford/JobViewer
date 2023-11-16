package ru.practicum.android.diploma.filter.presentation.view_model

import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

object UiConvertor {
    private fun convertCountryToCountryUi(country: Country) :AreaDataInterface.CountryUi {
        return AreaDataInterface.CountryUi(
            country.id,
            country.name
        )
    }

    fun convertCountryUiToCountry(country: AreaDataInterface) : Country{
        return Country(
            country.id,
            country.name
        )
    }

    fun convertCountryListToCountryUiList(list: List<Country>) : List<AreaDataInterface> {
       return list.map { country ->  convertCountryToCountryUi(country)}
    }
}
