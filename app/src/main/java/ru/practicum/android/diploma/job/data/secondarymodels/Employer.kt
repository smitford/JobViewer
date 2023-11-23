package ru.practicum.android.diploma.job.data.secondarymodels

import ru.practicum.android.diploma.search.data.models.LogoUrls

data class Employer(
    val id: String?,
    val logo_urls: LogoUrls?,
    val name: String,
    val alternate_url: String?
)