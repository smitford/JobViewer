package ru.practicum.android.diploma.job.domain.usecases.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.job.data.repositoryimpl.JobRepositoryImpl
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.use_cases.Adapter

class LoadJobUseCaseImpl(private val repository: JobRepositoryImpl) : LoadJobUseCase {
    override suspend fun getJob(id: String): Flow<JobForScreenInfo> =
        repository.getJob(id = id).map { result ->
            when (result) {
                is DtoConsumer.Success -> AdapterJobScreen.jobScreenInfoCreator(
                    resultCode = Codes.SUCCESS,
                    result.data
                )

                is DtoConsumer.Error -> Adapter.jobsInfoCreator(Codes.ERROR, null)

                is DtoConsumer.NoInternet -> Adapter.jobsInfoCreator(Codes.NO_NET_CONNECTION, null)
            }

        }
}