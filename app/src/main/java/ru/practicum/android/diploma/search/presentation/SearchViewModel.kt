package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.domain.use_cases.GetSearchFilterUseCase
import ru.practicum.android.diploma.search.domain.use_cases.LoadJobsUseCase
import ru.practicum.android.diploma.search.presentation.models.SearchStates
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val loadJobsUseCase: LoadJobsUseCase,
    private val getSearchFilterUseCase: GetSearchFilterUseCase
) : ViewModel() {
    private var filter: Filter =
        Filter(area = null, industry = null, salary = null, onlyWithSalary = false)

    init {
        filter = getFilter()
        Log.d("filterInit", filter.toString())
    }

    private var state: SearchStates = SearchStates.Start
    private val stateLiveData = MutableLiveData(state)
    private val vacancyList = mutableListOf<Vacancy>()
    private var searchJob: Job? = null
    private var page = 0
    private var maxPage = 1
    private val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            filter.request = it
            search()
        }

    fun loadJobs(text: String) {
        if (text.isBlank()) return
        searchDebounce(text)
    }

    private fun search() {
        stateLiveData.value = SearchStates.Loading
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                requestHandler(jobsInfo)
            }
        }
    }

    private fun refreshSearch() {
        vacancyList.clear()
        if (filter.request.isNotBlank()) search()
    }

    fun getState(): LiveData<SearchStates> = stateLiveData

    private fun getFilter() = getSearchFilterUseCase.execute()

    fun refreshFilter() {
        val newFiler = getFilter()
        Log.d("filterNew", newFiler.toString())
        if (newFiler != filter) {
            newFiler.request = filter.request
            filter = newFiler
            refreshSearch()
        }
    }

    fun getNewPage() {
        if (page < maxPage - 1) {
            filter.page = page + 1
            search()
        }
    }

    private fun requestHandler(jobsInfo: JobsInfo) {

        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                stateLiveData.value = SearchStates.ServerError
            }

            Codes.SUCCESS -> {
                vacancyList.addAll(jobsInfo.jobs!!)
                page = jobsInfo.page
                maxPage = jobsInfo.pages
                stateLiveData.value = jobsInfo.let {
                    SearchStates.Success(
                        jobList = vacancyList, page = it.page, found = it.found
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

    fun clearAll() {
        stateLiveData.value = SearchStates.Start
        vacancyList.clear()
        filter.request = ""
        searchJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        searchJob = null
    }

    companion object {
        const val SEARCH_DEBOUNCE_DELAY_MILS = 2000L
        const val STATUS_DEBOUNCE_DELAY_MILS = 100L
    }

}