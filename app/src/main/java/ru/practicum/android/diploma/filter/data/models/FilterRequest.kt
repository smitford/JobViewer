package ru.practicum.android.diploma.filter.data.models

sealed class FilterRequest {
    object Countries

    object Areas

    data class AreasById(val idArea: String)
}
