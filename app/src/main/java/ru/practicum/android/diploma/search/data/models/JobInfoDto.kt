package ru.practicum.android.diploma.search.data.models

import ru.practicum.android.diploma.util.Salary

data class JobInfoDto(
    val area: Area,
    val department: Department?,
    val employer: Employer,
    val id: String,
    val name: String,
    val salary: Salary?,
    val type: Type
)