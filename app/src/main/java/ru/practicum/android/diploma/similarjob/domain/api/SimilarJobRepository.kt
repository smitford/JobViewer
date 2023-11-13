package ru.practicum.android.diploma.similarjob.domain.api

import android.app.job.JobInfo
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.similarjob.SimilarJobState

interface SimilarJobRepository {
    fun getSimilarJobs(vacancyId:String): Flow<Pair<SimilarJobState, ArrayList<JobInfo>>>
}