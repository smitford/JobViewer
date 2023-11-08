package ru.practicum.android.diploma.job.data.mainmodels

import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.search.data.models.ResponseDto

data class JobDtoForScreenResponse(val item: JobForScreenInfo) : ResponseDto()