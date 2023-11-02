package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase

class SearchViewModel(
    private val filter: Filter,
    private val loadJobsUseCase: LoadJobsUseCase
) : ViewModel() {

    fun loadJobs() {
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect {
                changeState()
            }
        }
    }

    private fun changeState() {

    }
}