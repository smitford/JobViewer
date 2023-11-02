package ru.practicum.android.diploma.search.data.net

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.search.data.models.JobSearchResponseDto
import ru.practicum.android.diploma.search.data.models.ResultCodes
import ru.practicum.android.diploma.search.domain.api.JobNetworkRepository
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.util.NetworkClient

class JobNetworkRepositoryImp(private val networkClient: NetworkClient) : JobNetworkRepository {

    override suspend fun getJobs(
        filter: Filter
    ): Flow<DtoConsumer<List<Job>>> =
        flow {
            val response = networkClient.doRequest(AdapterJob.filterToJobReq(filter))
            when (response.responseCode) {
                ResultCodes.SUCCESS -> emit(
                    DtoConsumer.Success(
                        AdapterJob
                            .jobInfoDtoToJobInfo(
                                response = (response as JobSearchResponseDto)
                                    .jobsList
                            )
                    )
                )

                ResultCodes.NO_NET_CONNECTION -> emit(DtoConsumer.NoInternet(response.responseCode.code))
                ResultCodes.ERROR -> emit(DtoConsumer.Error(response.responseCode.code))
            }
        }.flowOn(Dispatchers.IO)


}