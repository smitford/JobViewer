package ru.practicum.android.diploma.search.domain.models

data class JobsInfo(
    val responseCodes: Codes,
    val jobs: List<Job>?,
    val found: Int = 0,
    val page: Int = 0,
    val pages: Int = 0
)
