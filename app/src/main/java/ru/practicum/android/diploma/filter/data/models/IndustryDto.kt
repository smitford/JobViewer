package ru.practicum.android.diploma.filter.data.models

data class IndustryDto(
    val id: String,
    val industries: List<IndustryDto>? = null,
    val name: String
)