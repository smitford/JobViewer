package ru.practicum.android.diploma.favorite.data.impl

import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository

class JobFavoriteRepositoryImpl(private val appDataBase: AppDataBase):JobFavoriteRepository {

    override suspend fun add(url: String) {
        //appDataBase.favoriteDAO().add()
    }

    override suspend fun delete(url: String) {
        //appDataBase.favoriteDAO().delete()
    }

    override suspend fun included(url: String): Boolean {
        return appDataBase.favoriteDAO().included(url)
    }
}