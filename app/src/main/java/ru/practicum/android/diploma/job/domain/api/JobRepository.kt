package ru.practicum.android.diploma.job.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface JobRepository {
    suspend fun getJob(id: String): Flow<DtoConsumer<JobForScreen>>
}