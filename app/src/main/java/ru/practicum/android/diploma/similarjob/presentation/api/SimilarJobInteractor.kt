package ru.practicum.android.diploma.similarjob.presentation.api

import android.app.job.JobInfo
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.similarjob.SimilarJobState

interface SimilarJobInteractor {
    fun getSimilarJobs(vacancyId:Long): Flow<Pair<SimilarJobState, ArrayList<JobInfo>>>
}