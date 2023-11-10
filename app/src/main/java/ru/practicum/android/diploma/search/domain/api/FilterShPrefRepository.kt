package ru.practicum.android.diploma.search.domain.api

import ru.practicum.android.diploma.search.domain.models.Filter

interface FilterShPrefRepository {
    fun getFilter(): Filter
}