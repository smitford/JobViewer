package ru.practicum.android.diploma.filter.domain.models

data class FilterParameters(
    val country: String?,
    val areaId: String?,
    val area: String?,
    val industryId: String?,
    val industry: String?,
    val salary: Int?,
    val onlyWithSalary: Boolean
)
