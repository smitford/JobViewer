package ru.practicum.android.diploma.job.presentation

import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.search.presentation.models.SearchStates

internal interface JobStates {
    object Default : JobStates
    object Loading : JobStates
    object ServerError: JobStates
    object ConnectionError : JobStates
    object InvalidRequest : JobStates
    data class Success(val jobForScreen: JobForScreen) : JobStates
}