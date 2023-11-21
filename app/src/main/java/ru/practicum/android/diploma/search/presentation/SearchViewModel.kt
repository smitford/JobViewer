package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
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
    }

    private var state: SearchStates = SearchStates.Start(checkFilterState())
    private val stateLiveData = MutableLiveData(state)
    private val vacancyList = mutableListOf<Vacancy>()
    private var searchJob: Job? = null
    private var page = 0
    private var maxPage = 1
    private val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            filter.request = it
            search(false)
        }
    private val pageLoaderDebounce =
        debounce<Unit>(PAGE_LOAD_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            search(true)
        }

    fun loadJobs(text: String) {
        if (text.isBlank() || text == filter.request) return
        searchDebounce(text)
    }

    private fun search(pageRefresher: Boolean) {
        stateLiveData.value = SearchStates.Loading(pageRefresher)
        viewModelScope.launch {
            loadJobsUseCase.execute(filter = filter).collect { jobsInfo ->
                requestHandler(jobsInfo)
            }
        }
    }

    private fun refreshSearch() {
        vacancyList.clear()
        if (filter.request.isNotBlank()) search(false)
    }

    fun getState(): LiveData<SearchStates> = stateLiveData

    private fun getFilter() = getSearchFilterUseCase.execute()

    fun refreshFilter() {
        val newFiler = getFilter()
        newFiler.request = filter.request
        newFiler.page = filter.page
        if (newFiler != filter) {
            newFiler.page = 0
            when (stateLiveData.value) {
                is SearchStates.Success -> (stateLiveData.value as SearchStates.Success).filterStates =
                    checkFilterState()

                is SearchStates.Start -> (stateLiveData.value as SearchStates.Start).filterStates =
                    checkFilterState()

                is SearchStates.ServerError -> (stateLiveData.value as SearchStates.ServerError).filterStates =
                    checkFilterState()

                is SearchStates.InvalidRequest -> (stateLiveData.value as SearchStates.InvalidRequest).filterStates =
                    checkFilterState()

                is SearchStates.ConnectionError -> (stateLiveData.value as SearchStates.ConnectionError).filterStates =
                    checkFilterState()

                else -> Unit
            }
            filter = newFiler
            refreshSearch()
        }
    }

    fun getNewPage() {
        if (page < maxPage - 1) {
            filter.page = page + 1
            pageLoaderDebounce(Unit)
        }
    }

    private fun requestHandler(jobsInfo: JobsInfo) {
        when (jobsInfo.responseCodes) {
            Codes.ERROR -> {
                stateLiveData.value = SearchStates.ServerError(checkFilterState())
            }

            Codes.SUCCESS -> {
                vacancyList.addAll(jobsInfo.jobs!!)
                page = jobsInfo.page
                maxPage = jobsInfo.pages
                stateLiveData.value = jobsInfo.let {
                    SearchStates.Success(
                        jobList = vacancyList,
                        page = it.page,
                        found = it.found,
                        filterStates = checkFilterState()
                    )
                }
            }

            Codes.NO_NET_CONNECTION -> {
                stateLiveData.value = SearchStates.ConnectionError(checkFilterState())
            }

            Codes.NO_RESULTS -> {
                stateLiveData.value = SearchStates.InvalidRequest(checkFilterState())
            }
        }
    }

    private fun checkFilterState(): Boolean =
        filter.salary != null || filter.area != null || filter.industry != null || filter.onlyWithSalary

    fun clearAll() {
        stateLiveData.value = SearchStates.Start(checkFilterState())
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
        const val PAGE_LOAD_DEBOUNCE_DELAY_MILS = 100L
    }

}