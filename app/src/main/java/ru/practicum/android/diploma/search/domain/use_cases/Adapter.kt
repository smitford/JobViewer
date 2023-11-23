package ru.practicum.android.diploma.search.domain.use_cases

import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.search.domain.models.JobsInfo

object Adapter {
    fun jobsInfoCreator(resultCode: Codes, jobs: List<Job>?)= JobsInfo (
        responseCodes =resultCode,
        jobs
    )
}