package ru.practicum.android.diploma.search.presentation.models

import ru.practicum.android.diploma.search.domain.models.Job

sealed interface SearchStates {
    data object Default : SearchStates
    data object Loading : SearchStates
    data object LoadingPaging : SearchStates
    data object ServerError : SearchStates
    data object ConnectionError : SearchStates
    data object InvalidRequest : SearchStates
    data class Success(val jobList: List<Job>, val page: Int, val found: Int) : SearchStates
}