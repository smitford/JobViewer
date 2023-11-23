package ru.practicum.android.diploma.job.domain.models

import ru.practicum.android.diploma.job.data.secondarymodels.Address
import ru.practicum.android.diploma.job.data.secondarymodels.Phones
import ru.practicum.android.diploma.job.data.secondarymodels.Skills

data class JobForScreen(
    val id: String?,
    val name: String?,
    val salaryFrom: String?,
    val employerLogoUrl: String?,
    val employerUrl: String?,
    val employerName: String?,
    val area: String?,
    val address: String?,
    val experience: String?,
    val employment: String?,
    val description: String?,
    val keySkills: Array<Skills?>,
    val contactsName: String?,
    val email: String?,
    val phones: Array<Phones?>?
)