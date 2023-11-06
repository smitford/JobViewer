package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchViewModel(
    private val filter: Filter,
    private val loadJobsUseCase: LoadJobsUseCase
) : ViewModel() {

    private var state: SearchStates = SearchStates.Default
    private val stateLiveData = MutableLiveData(state)

    fun loadJobs() {
        if (filter.request.isBlank()) return
        stateLiveData.value = SearchStates.Loading
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                requestHandler(jobsInfo)
            }
        }
    }

    fun getState() = stateLiveData

    private fun requestHandler(jobsInfo: JobsInfo) =
        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                stateLiveData.value = SearchStates.ServerError
                Log.d("server error", jobsInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                stateLiveData.value =
                    jobsInfo.jobs?.let { SearchStates.Success(it) } ?: SearchStates.InvalidRequest
                Log.d("success", jobsInfo.responseCodes.name)
            }

            Codes.NO_NET_CONNECTION -> {
                stateLiveData.value = SearchStates.ConnectionError
                Log.d("internet error", jobsInfo.responseCodes.name)
            }
        }


}