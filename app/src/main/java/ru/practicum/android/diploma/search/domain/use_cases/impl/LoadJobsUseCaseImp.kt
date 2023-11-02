package ru.practicum.android.diploma.search.domain.use_cases.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.api.JobNetworkRepository
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.Adapter
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase

class LoadJobsUseCaseImp(val repository: JobNetworkRepository) : LoadJobsUseCase {
    override suspend fun execute(filter: Filter): Flow<JobsInfo> =
        repository.getJobs(filter = filter).map { result ->
            when (result) {
                is DtoConsumer.Success -> Adapter.jobsInfoCreator(
                    resultCode = Codes.SUCCESS,
                    result.data
                )

                is DtoConsumer.Error -> Adapter.jobsInfoCreator(Codes.ERROR, null)

                is DtoConsumer.NoInternet -> Adapter.jobsInfoCreator(Codes.NO_NET_CONNECTION, null)
            }

        }

}