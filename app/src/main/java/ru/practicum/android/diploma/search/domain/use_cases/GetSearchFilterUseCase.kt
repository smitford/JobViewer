package ru.practicum.android.diploma.search.domain.use_cases

import ru.practicum.android.diploma.search.domain.models.Filter

interface GetSearchFilterUseCase {
    fun execute(): Filter
}