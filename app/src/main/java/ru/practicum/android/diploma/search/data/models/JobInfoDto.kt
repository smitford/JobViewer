package ru.practicum.android.diploma.search.data.models

data class JobInfoDto(
    val area: Area,
    val department: Department?,
    val employer: Employer,
    val id: String,
    val name: String,
    val salary: Salary?,
    val type: Type
)