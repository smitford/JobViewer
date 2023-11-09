package ru.practicum.android.diploma.job.presentation.states

import ru.practicum.android.diploma.job.domain.models.JobForScreen

internal interface JobStates {
    object Default : JobStates
    object Loading : JobStates
    object ServerError : JobStates
    object ConnectionError : JobStates
    object InvalidRequest : JobStates
    data class Success(val jobForScreen: JobForScreen) : JobStates
}