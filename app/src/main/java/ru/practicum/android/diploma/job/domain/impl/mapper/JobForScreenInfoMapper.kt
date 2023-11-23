package ru.practicum.android.diploma.job.domain.impl.mapper

import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.domain.models.Codes

object JobForScreenInfoMapper {
    fun createJobForScreenInfo(resultCode: Codes, job: JobForScreen?) = JobForScreenInfo(
        responseCodes = resultCode,
        job = job
    )
}