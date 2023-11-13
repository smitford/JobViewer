package ru.practicum.android.diploma.similarjob.data

import android.app.job.JobInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.search.data.models.JobSearchResponseDto
import ru.practicum.android.diploma.search.data.models.ResultCodes
import ru.practicum.android.diploma.search.data.net.AdapterJob
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.similarjob.SimilarJobState
import ru.practicum.android.diploma.similarjob.domain.api.SimilarJobRepository
import ru.practicum.android.diploma.util.NetworkClient

class SimilarJobRepositoryImpl(private val networkClient: NetworkClient): SimilarJobRepository {

    override fun getSimilarJobs(vacancyId: String): Flow<Pair<SimilarJobState, ArrayList<JobInfo>>> = flow {
        val response = networkClient.doRequest()
        when (response.responseCode) {
            ResultCodes.SUCCESS -> emit(
                DtoConsumer.Success(
                    AdapterJob
                        .jobInfoDtoToJobInfo(
                            code = response.responseCode.code,
                            response = (response as JobSearchResponseDto)
                        )
                )
            )

            ResultCodes.NO_NET_CONNECTION -> emit(DtoConsumer.NoInternet(response.responseCode.code))
            ResultCodes.ERROR -> emit(DtoConsumer.Error(response.responseCode.code))
        }
    }.flowOn(Dispatchers.IO)

        // Работа с сетью


    }


