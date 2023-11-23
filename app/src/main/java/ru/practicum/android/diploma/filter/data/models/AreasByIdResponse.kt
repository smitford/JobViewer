package ru.practicum.android.diploma.filter.data.models

import ru.practicum.android.diploma.search.data.models.ResponseDto

data class AreasByIdResponse(
    val results: AreaDto
) : ResponseDto()