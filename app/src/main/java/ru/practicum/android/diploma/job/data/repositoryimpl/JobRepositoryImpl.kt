package ru.practicum.android.diploma.job.data.repositoryimpl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenRequest
import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.job.data.mapper.JobForScreenMapper
import ru.practicum.android.diploma.job.domain.api.JobDtoConsumer
import ru.practicum.android.diploma.job.domain.api.JobRepository
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.search.data.models.ResultCodes
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.NetworkClient

class JobRepositoryImpl(private val networkClient: NetworkClient) : JobRepository {
    override suspend fun getJob(
        id: String
    ): Flow<DtoConsumer<JobForScreen>> =
        flow {
            val response = networkClient.doRequest(JobDtoForScreenRequest(id))
            when (response.responseCode) {
                ResultCodes.SUCCESS -> emit(
                    DtoConsumer.Success(
                        JobForScreenMapper
                            .mapToJobForScreen(
                                response = (response as JobDtoForScreenResponse)
                                    .item
                            )
                    )
                )

                ResultCodes.NO_NET_CONNECTION -> emit(DtoConsumer.NoInternet(response.responseCode.code))
                ResultCodes.ERROR -> emit(DtoConsumer.Error(response.responseCode.code))
            }
        }.flowOn(Dispatchers.IO)
}