package ru.practicum.android.diploma.filter.presentation.adapter.model

sealed class AreaDataInterface(
    val id: String,
    val name: String
) {

    class CountryUi(
        id: String, name: String
    ) : AreaDataInterface(id, name)

    class RegionUi(
        id: String, name: String,
        val countryName: String,
        val countryId: String
    ) : AreaDataInterface(id, name)

    class IndustryUi(
        id: String, name: String,
        var isSelected: Boolean = false
    ) : AreaDataInterface(id, name)
}