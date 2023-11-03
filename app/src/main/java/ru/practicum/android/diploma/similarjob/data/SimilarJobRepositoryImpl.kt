package ru.practicum.android.diploma.similarjob.data

import android.app.job.JobInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.similarjob.SimilarJobState
import ru.practicum.android.diploma.similarjob.domain.api.SimilarJobRepository

class SimilarJobRepositoryImpl: SimilarJobRepository {
    override fun getSimilarJobs(vacancyId: Long): Flow<Pair<SimilarJobState, ArrayList<JobInfo>>> = flow {

        // Работа с сетью


    }






}