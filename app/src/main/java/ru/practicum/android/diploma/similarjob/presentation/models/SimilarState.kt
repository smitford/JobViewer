package ru.practicum.android.diploma.similarjob.presentation.models

import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface SimilarState {
    data class Loading(var pageRefresher: Boolean) : SimilarState
    data object ServerError : SimilarState
    data object ConnectionError : SimilarState
    data object InvalidRequest : SimilarState
    data class Success(
        val jobList: List<Vacancy>,
    ) : SimilarState
}