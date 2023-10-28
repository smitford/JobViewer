package ru.practicum.android.diploma.favorite.presentation.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.StateFavorite

interface FavoriteInteractor {

    fun getFavoriteTracks() : Flow<Pair<StateFavorite,ArrayList<FavoriteTrack>>>

}