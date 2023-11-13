package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Job
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.GetSearchFilterUseCase
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchViewModel(
    private val loadJobsUseCase: LoadJobsUseCase,
    private val getSearchFilterUseCase: GetSearchFilterUseCase
) : ViewModel() {
    private var filter: Filter =
        Filter(area = null, industry = null, salary = null, onlyWithSalary = false)

    init {
        filter = getFilter()
    }

    private var state: SearchStates = SearchStates.Default
    private val stateLiveData = MutableLiveData(state)
    private val jobList = mutableListOf<Job>()
    private var page = 0

    fun loadJobs(text: String) {
        if (text.isBlank()) return
        jobList.clear()
        filter.request = text
        search()
    }

    private fun search() {
        stateLiveData.value = SearchStates.LoadingPaging
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                requestHandler(jobsInfo)
            }
        }
    }

    private fun refreshSearch() {
        jobList.clear()
        if (filter.request.isBlank()) return else search()
    }


    fun getState() = stateLiveData

    private fun getFilter() = getSearchFilterUseCase.execute()

    fun refreshFilter() {
        val newFiler = getFilter()
        if (newFiler != filter) {
            newFiler.request = filter.request
            filter = newFiler
            refreshSearch()
        }
    }

    fun getNewPage() {
        filter.page = page + 1
        search()
    }

    private fun requestHandler(jobsInfo: JobsInfo) {
        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                stateLiveData.value = SearchStates.ServerError
            }

            Codes.SUCCESS -> {
                jobList.addAll(jobsInfo.jobs!!)
                page = jobsInfo.page
                stateLiveData.value =
                    jobsInfo.let {
                        SearchStates.Success(
                            jobList = jobList,
                            page = it.page,
                            found = it.found
                        )
                    }
            }

            Codes.NO_NET_CONNECTION -> {
                stateLiveData.value = SearchStates.ConnectionError
            }

            Codes.NO_RESULTS -> {
                stateLiveData.value = SearchStates.InvalidRequest
            }
        }
    }


}