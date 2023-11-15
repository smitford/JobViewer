package ru.practicum.android.diploma.favorite.data.impl

import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.data.db.mapper.JobMapper
import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository
import ru.practicum.android.diploma.job.domain.models.JobForScreen

class JobFavoriteRepositoryImpl(private val appDataBase: AppDataBase) : JobFavoriteRepository {

    override suspend fun add(job: JobForScreen) {

        val mapper = JobMapper()
        appDataBase.favoriteDAO().add(mapper.map(job))

    }

    override suspend fun delete(id: String) {
        appDataBase.favoriteDAO().delete(id)
    }

    override suspend fun included(id: String): Boolean {

        return try {
            appDataBase.favoriteDAO().included(id)
        } catch (e: Exception) {
            false
        }

    }

    override suspend fun getFromBase(id: String): JobForScreen {
        TODO("Not yet implemented")
    }

}