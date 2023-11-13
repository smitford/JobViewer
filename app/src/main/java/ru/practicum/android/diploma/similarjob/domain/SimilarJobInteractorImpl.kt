package ru.practicum.android.diploma.similarjob.domain

import android.app.job.JobInfo
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.similarjob.SimilarJobState
import ru.practicum.android.diploma.similarjob.domain.api.SimilarJobRepository
import ru.practicum.android.diploma.similarjob.presentation.api.SimilarJobInteractor

class SimilarJobInteractorImpl(private val similarJobRepository: SimilarJobRepository): SimilarJobInteractor {
    override fun getSimilarJobs(vacancyId: String): Flow<Pair<SimilarJobState, ArrayList<JobInfo>>> {
        return similarJobRepository.getSimilarJobs(vacancyId)
    }

}