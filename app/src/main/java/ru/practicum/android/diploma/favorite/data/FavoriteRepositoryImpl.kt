package ru.practicum.android.diploma.favorite.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.StateFavorite
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository

class FavoriteRepositoryImpl: FavoriteRepository {
    override fun getFavoriteTracks(): Flow<Pair<StateFavorite, ArrayList<FavoriteTrack>>> = flow{

        emit(Pair(StateFavorite.FULL, arrayListOf()))

        // Работа с БД

    }
}