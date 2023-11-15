package ru.practicum.android.diploma.job.data.impl.mapper

sealed class TypeForTextUtils {
    data object Comment : TypeForTextUtils()
    data object Phones : TypeForTextUtils()
    data object Skills : TypeForTextUtils()
}