package ru.practicum.android.diploma.favorite.presentation.api

import ru.practicum.android.diploma.job.domain.models.JobForScreen

interface JobFavoriteInteractor {
    suspend fun add(job: JobForScreen)
    suspend fun delete(id: String)
    suspend fun included(id: String): Boolean
    suspend fun getFromBase(id: String): JobForScreen
}