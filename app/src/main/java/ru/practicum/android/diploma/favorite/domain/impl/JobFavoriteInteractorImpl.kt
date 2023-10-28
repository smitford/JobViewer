package ru.practicum.android.diploma.favorite.domain.impl


import ru.practicum.android.diploma.favorite.domain.api.JobFavoriteRepository
import ru.practicum.android.diploma.favorite.presentation.api.JobFavoriteInteractor

class JobFavoriteInteractorImpl(private val jobFavoriteRepository: JobFavoriteRepository):JobFavoriteInteractor {
    override suspend fun add(url: String) {
        jobFavoriteRepository.add(url)
    }

    override suspend fun delete(url: String) {
        jobFavoriteRepository.delete(url)
    }

    override suspend fun included(url: String): Boolean {
        return jobFavoriteRepository.included(url)
    }
}