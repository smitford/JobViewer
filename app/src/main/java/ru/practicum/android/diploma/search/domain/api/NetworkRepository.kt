package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.consumer.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.JobInfo

interface NetworkRepository{

    fun getJobs(term: String): Flow<DtoConsumer<List<JobInfo>>>
}