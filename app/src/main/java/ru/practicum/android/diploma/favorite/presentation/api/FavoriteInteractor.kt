package ru.practicum.android.diploma.favorite.presentation.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.FavoriteState

interface FavoriteInteractor {

    fun get() : Flow<Pair<FavoriteState,ArrayList<FavoriteTrack>>>

}