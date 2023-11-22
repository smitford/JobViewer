package ru.practicum.android.diploma.job.presentation.states

import ru.practicum.android.diploma.job.domain.models.JobForScreen

sealed class JobScreenState {
    data object Loading : JobScreenState()
    data object ServerError : JobScreenState()
    data object ConnectionError : JobScreenState()
    data object InvalidRequest : JobScreenState()
    data class Success(val jobForScreen: JobForScreen) : JobScreenState()
    data class FavouriteIcon(val isFavourite: Boolean) : JobScreenState()
    data class JobFromDb(val jobForScreenDb: JobForScreen) : JobScreenState()
}