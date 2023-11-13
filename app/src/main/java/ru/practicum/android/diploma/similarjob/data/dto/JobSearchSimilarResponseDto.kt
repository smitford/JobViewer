package ru.practicum.android.diploma.similarjob.data.dto

import ru.practicum.android.diploma.search.data.models.JobInfoDto
import ru.practicum.android.diploma.search.data.models.ResponseDto

data class JobSearchSimilarResponseDto(
    val items: List<JobInfoDto>,
    val found: Int,
    val page: Int,
    val pages: Int
) : ResponseDto()
