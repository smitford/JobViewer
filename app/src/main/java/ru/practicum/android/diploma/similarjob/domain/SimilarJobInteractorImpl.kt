package ru.practicum.android.diploma.similarjob.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.AdapterDomain
import ru.practicum.android.diploma.similarjob.domain.api.SimilarJobRepository
import ru.practicum.android.diploma.similarjob.presentation.api.SimilarJobInteractor

class SimilarJobInteractorImpl(private val similarJobRepository: SimilarJobRepository) :
    SimilarJobInteractor {
    override fun getSimilarJobs(vacancyId: String, page: Int): Flow<JobsInfo> {
        return similarJobRepository.getSimilarJobs(vacancyId, page).map { result ->
            when (result) {
                is DtoConsumer.Success -> result.data
                is DtoConsumer.Error -> AdapterDomain.codeMapper(Codes.ERROR)
                is DtoConsumer.NoInternet -> AdapterDomain.codeMapper(Codes.NO_NET_CONNECTION)
            }
        }
    }
}