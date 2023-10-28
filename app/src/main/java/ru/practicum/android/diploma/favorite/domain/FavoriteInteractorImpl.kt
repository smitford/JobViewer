package ru.practicum.android.diploma.favorite.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor

class FavoriteInteractorImpl(private val favoriteRepository: FavoriteRepository):FavoriteInteractor {
    override fun getFavoriteTracks(): Flow<Pair<StateFavorite, ArrayList<FavoriteTrack>>> {
        return favoriteRepository.getFavoriteTracks()
    }
}