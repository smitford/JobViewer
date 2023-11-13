package ru.practicum.android.diploma.search.data.models

data class JobSearchResponseDto(
    val items: List<JobInfoDto>,
    val found: Int,
    val page: Int,
    val pages: Int
) : ResponseDto()