package ru.practicum.android.diploma.favorite.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.FavoriteState

interface FavoriteRepository {
    fun get() : Flow<Pair<FavoriteState, ArrayList<FavoriteTrack>>>
}