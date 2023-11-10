package ru.practicum.android.diploma.job.data.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.job.data.impl.mapper.JobForScreenMapper
import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenRequest
import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.domain.repository.JobRepository
import ru.practicum.android.diploma.search.data.models.ResultCodes
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.NetworkClient

class JobRepositoryImpl(
    private val networkClient: NetworkClient,
    private val jobForScreenMapper: JobForScreenMapper
) : JobRepository {
    override suspend fun getJob(
        id: String
    ): Flow<DtoConsumer<JobForScreen>> =
        flow {
            val response =
                networkClient.doRequest(JobDtoForScreenRequest(id))

            when (response.responseCode) {
                ResultCodes.SUCCESS -> emit(
                    DtoConsumer.Success(
                        jobForScreenMapper.mapToJobForScreen(response as JobDtoForScreenResponse)
                    )
                )

                ResultCodes.NO_NET_CONNECTION -> emit(DtoConsumer.NoInternet(response.responseCode.code))
                ResultCodes.ERROR -> emit(DtoConsumer.Error(response.responseCode.code))
            }
        }.flowOn(Dispatchers.IO)
}