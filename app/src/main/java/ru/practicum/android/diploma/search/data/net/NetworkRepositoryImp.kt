package ru.practicum.android.diploma.search.data.net

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.api.NetworkRepository
import ru.practicum.android.diploma.search.domain.consumer.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.JobInfo

class NetworkRepositoryImp(networkClient: NetworkClient): NetworkRepository {

    override fun getJobs(term: String): Flow<DtoConsumer<List<JobInfo>>> {
        TODO("Not yet implemented")
    }

    companion object{
        const val BASE_HH_API = "https://api.hh.ru/"
    }
}