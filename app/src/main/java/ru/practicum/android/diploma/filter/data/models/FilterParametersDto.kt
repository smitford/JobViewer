package ru.practicum.android.diploma.filter.data.models

data class FilterParametersDto(
    val country: String?,
    val areaId: String?,
    val area: String?,
    val industryId: String?,
    val industry: String?,
    val salary: String?,
    val onlyWithSalary: Boolean
)