package ru.practicum.android.diploma.job.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.JobsInfo

interface LoadJobUseCase {
    suspend fun getJob(id: String): Flow<Any>
}