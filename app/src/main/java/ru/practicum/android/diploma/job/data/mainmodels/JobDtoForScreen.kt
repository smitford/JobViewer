package ru.practicum.android.diploma.job.data.mainmodels

import ru.practicum.android.diploma.job.data.secondarymodels.Area
import ru.practicum.android.diploma.job.data.secondarymodels.Contacts
import ru.practicum.android.diploma.job.data.secondarymodels.Employer
import ru.practicum.android.diploma.job.data.secondarymodels.Employment
import ru.practicum.android.diploma.job.data.secondarymodels.Experience
import ru.practicum.android.diploma.job.data.secondarymodels.Salary


data class JobDtoForScreen(
    val id: String,
    val name: String,
    val salary: Salary?,
    val employer: Employer,
    val area: Area,
    val experience: Experience,
    val employment: Employment,
    val description: String?,
    val keySkills: List<String>,
    val contacts: Contacts?,
)