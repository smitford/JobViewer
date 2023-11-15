package ru.practicum.android.diploma.favorite.domain.impl

import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.api.JobFavoriteInteractor
import ru.practicum.android.diploma.job.domain.models.JobForScreen

class JobFavoriteInteractorImpl(private val jobFavoriteRepository: JobFavoriteRepository):JobFavoriteInteractor {
    override suspend fun add(job: JobForScreen) {
        jobFavoriteRepository.add(job)
    }

    override suspend fun getFromBase(id: String): JobForScreen {
        return jobFavoriteRepository.getFromBase(id)
    }

    override suspend fun delete(id: String) {
        jobFavoriteRepository.delete(id)
    }

    override suspend fun included(id: String): Boolean {
        return jobFavoriteRepository.included(id)
    }
}