package ru.practicum.android.diploma.search.presentation.models

import ru.practicum.android.diploma.search.domain.models.Vacancy

sealed interface SearchStates {
    data class FilterIconStatus(val isOn: Boolean) : SearchStates
    data object Loading : SearchStates
    data class ServerError(var filterStates: Boolean) : SearchStates
    data class ConnectionError(var filterStates: Boolean) : SearchStates
    data class InvalidRequest(var filterStates: Boolean) : SearchStates
    data class Success(
        val jobList: List<Vacancy>,
        val page: Int,
        val found: Int,
        var filterStates: Boolean
    ) : SearchStates
}