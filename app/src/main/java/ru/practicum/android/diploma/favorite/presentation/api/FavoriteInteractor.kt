package ru.practicum.android.diploma.favorite.presentation.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteState
import ru.practicum.android.diploma.search.domain.models.Job

interface FavoriteInteractor {
    fun get() : Flow<Pair<FavoriteState, ArrayList<Job>>>
}