package ru.practicum.android.diploma.job.data.impl.mapper

sealed class TypeForMapper {
    data object Comment : TypeForMapper()
    data object Phones : TypeForMapper()
    data object Skills : TypeForMapper()
}