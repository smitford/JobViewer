package ru.practicum.android.diploma.job.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo

interface LoadJobInteractor {
    suspend fun getJob(id: String): Flow<JobForScreenInfo>
}