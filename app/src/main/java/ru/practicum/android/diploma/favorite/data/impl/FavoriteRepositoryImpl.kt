package ru.practicum.android.diploma.favorite.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.domain.FavoriteTrack
import ru.practicum.android.diploma.favorite.domain.FavoriteState
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository

class FavoriteRepositoryImpl(private val appDataBase: AppDataBase): FavoriteRepository {

    override fun get(): Flow<Pair<FavoriteState, ArrayList<FavoriteTrack>>> = flow{

        //val jobs =  appDataBase.favoriteDAO().get()

        emit(Pair(FavoriteState.FULL, arrayListOf()))

        // Работа с БД

    }
}