package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job

interface JobNetworkRepository{

    suspend fun getJobs(filter: Filter): Flow<DtoConsumer<List<Job>>>
}