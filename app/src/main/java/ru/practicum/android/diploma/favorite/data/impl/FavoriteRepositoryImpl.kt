package ru.practicum.android.diploma.favorite.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.data.db.mapper.JobMapper
import ru.practicum.android.diploma.favorite.domain.FavoriteState
import ru.practicum.android.diploma.favorite.domain.api.FavoriteRepository
import ru.practicum.android.diploma.search.domain.models.Vacancy

class FavoriteRepositoryImpl(private val appDataBase: AppDataBase,private val mapper: JobMapper): FavoriteRepository {

    override fun get(): Flow<Pair<FavoriteState, ArrayList<Vacancy>>> = flow{

        try {
            val result = appDataBase.favoriteDAO().get()
            if (result.isEmpty()) {
                emit(Pair(FavoriteState.EMPTY, arrayListOf()))
            } else {

                val vacancies = ArrayList<Vacancy>()

                result.forEach {
                    vacancies.add(mapper.mapJob(it))
                }
                emit(Pair(FavoriteState.FULL, vacancies))
            }

        } catch (e: Exception) {
            emit(Pair(FavoriteState.ERROR, arrayListOf()))
        }

    }
}