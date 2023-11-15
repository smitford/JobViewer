package ru.practicum.android.diploma.favorite.data.impl

import ru.practicum.android.diploma.favorite.data.db.AppDataBase
import ru.practicum.android.diploma.favorite.data.db.mapper.JobMapper
import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository
import ru.practicum.android.diploma.job.domain.models.JobForScreen

class JobFavoriteRepositoryImpl(private val appDataBase: AppDataBase) : JobFavoriteRepository {

    override suspend fun add(job: JobForScreen) {

        val mapper = JobMapper()
        appDataBase.favoriteDAO().add(mapper.map(job))

        // сохранить навыки
        job.keySkills.forEach {
            appDataBase.KeySkillsDAO().add(mapper.mapSkills(it!!, job.id!!))
        }

        // сохранить контакты
        job.phones?.forEach {
            appDataBase.PhonesDAO().add(mapper.mapPhones(it!!, job.id!!))
        }

    }

    override suspend fun delete(id: String) {
        appDataBase.favoriteDAO().delete(id)
        appDataBase.KeySkillsDAO().delete(id)
        appDataBase.PhonesDAO().delete(id)
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