package ru.practicum.android.diploma.favorite.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.favorite.domain.FavoriteState
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.api.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoriteInteractorImpl(private val favoriteRepository: FavoriteRepository):FavoriteInteractor {
    override fun get(): Flow<Pair<FavoriteState, ArrayList<Vacancy>>> {
        return favoriteRepository.get()
    }
}