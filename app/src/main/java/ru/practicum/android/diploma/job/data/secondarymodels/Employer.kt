package ru.practicum.android.diploma.job.data.secondarymodels

import ru.practicum.android.diploma.search.data.models.LogoUrls

data class Employer(
    val id: String?,
    val logoUrls: LogoUrls?,
    val name: String,
    val url: String?
)