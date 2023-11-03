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
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                changeState(jobsInfo)
            }
        }
    }

    private fun changeState(jobsInfo: JobsInfo) =
        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                state = SearchStates.ServerError
                Log.d("server error", jobsInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                state =
                    jobsInfo.jobs?.let { SearchStates.Success(it) } ?: SearchStates.InvalidRequest
                Log.d("success", jobsInfo.responseCodes.name)
            }

            Codes.NO_NET_CONNECTION -> {
                state = SearchStates.ConnectionError
                Log.d("internet error", jobsInfo.responseCodes.name)
            }
        }


}