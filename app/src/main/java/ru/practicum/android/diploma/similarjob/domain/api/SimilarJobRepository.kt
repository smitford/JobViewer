package ru.practicum.android.diploma.similarjob.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.JobsInfo

interface SimilarJobRepository {
    fun getSimilarJobs(vacancyId: String): Flow<DtoConsumer<JobsInfo>>
}