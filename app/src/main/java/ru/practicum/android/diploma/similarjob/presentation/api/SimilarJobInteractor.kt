package ru.practicum.android.diploma.similarjob.presentation.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.JobsInfo

interface SimilarJobInteractor {
    fun getSimilarJobs(vacancyId:String): Flow<DtoConsumer<JobsInfo>>
}