package ru.practicum.android.diploma.job.domain.usecases.impl

import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.search.domain.models.JobsInfo

object AdapterJobScreen {
    fun jobScreenInfoCreator(resultCode: Codes, job: JobForScreen) = JobForScreenInfo(
        responseCodes = resultCode,
        job
    )
}