package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.GetSearchFilterUseCase
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchViewModel(
    private val loadJobsUseCase: LoadJobsUseCase,
    private val getSearchFilterUseCase: GetSearchFilterUseCase
) : ViewModel() {

    private var filter: Filter = Filter(
        page = 0,
        request = "android",
        area = null,
        industry = null,
        salary = null,
        onlyWithSalary = false
    )
    private var state: SearchStates = SearchStates.Default
    private val stateLiveData = MutableLiveData(state)

    fun loadJobs(text: String) {
        if (text.isBlank()) return
        filter.request = text
        stateLiveData.value = SearchStates.Loading
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                requestHandler(jobsInfo)
            }
        }
    }

    fun getFilterRequest(): String = filter.request

    fun getState() = stateLiveData

    private fun requestHandler(jobsInfo: JobsInfo) {
        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                stateLiveData.value = SearchStates.ServerError
                Log.d("server error", jobsInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                stateLiveData.value =
                    jobsInfo.let {
                        SearchStates.Success(
                            jobList = it.jobs ?: listOf(),
                            page = it.page,
                            found = it.found
                        )
                    }
                Log.d("success", jobsInfo.responseCodes.name)
            }

            Codes.NO_NET_CONNECTION -> {
                stateLiveData.value = SearchStates.ConnectionError
                Log.d("internet error", jobsInfo.responseCodes.name)
            }

            Codes.NO_RESULTS -> {
                stateLiveData.value = SearchStates.InvalidRequest
            }
        }
    }


}