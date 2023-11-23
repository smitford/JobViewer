package ru.practicum.android.diploma.search.data.models

data class FilterSp(
    val areaId: String? = null,
    val industryId: String? = null,
    val salary: String? = null,
    val onlyWithSalary: Boolean = false
)