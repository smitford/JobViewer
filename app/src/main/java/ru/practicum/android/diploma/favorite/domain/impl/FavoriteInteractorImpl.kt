package ru.practicum.android.diploma.favorite.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.StateFavorite
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor

class FavoriteInteractorImpl(private val favoriteRepository: FavoriteRepository):FavoriteInteractor {
    override fun get(): Flow<Pair<StateFavorite, ArrayList<FavoriteTrack>>> {
        return favoriteRepository.get()
    }
}