package ru.practicum.android.diploma.filter.presentation.view_model.model

import ru.practicum.android.diploma.filter.domain.models.Country

sealed interface FilterParametersState {
    data object Loading : FilterParametersState

    data class ParametersResult(
        val list: List<Country>
    ) : FilterParametersState

    data class Error(
        val errorMessage: String
    ) : FilterParametersState
}