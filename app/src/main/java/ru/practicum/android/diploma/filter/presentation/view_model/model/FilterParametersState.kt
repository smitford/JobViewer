package ru.practicum.android.diploma.filter.presentation.view_model.model

import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface

sealed interface FilterParametersState {
    data object Loading : FilterParametersState

    data class ParametersResult(
        val list: List<AreaDataInterface>
    ) : FilterParametersState

    data class Error(
        val errorMessage: String
    ) : FilterParametersState
}