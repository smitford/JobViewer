package ru.practicum.android.diploma.search.data.models

data class JobSearchRequest(
    val page: Int,
    val perPage: Int,
    val text: String,
    val area: String?,
    val industry: String?,
    val salary: Int?,
    val onlyWithSalary: Boolean
)