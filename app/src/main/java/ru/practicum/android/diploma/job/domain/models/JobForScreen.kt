package ru.practicum.android.diploma.job.domain.models

import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skill

data class JobForScreen(
    val id: String?,
    val name: String?,
    val salaryFrom: String?,
    val employerUrl: String?,
    val employerName: String?,
    val area: String?,
    val experience: String?,
    val employment: String?,
    val description: String?,
    val keySkills: List<Skill?>,
    val contactsName: String?,
    val email: String?,
    val phonesNumber: List<Phones>?
)