package ru.practicum.android.diploma.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.job.presentation.states.JobScreenState
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
    private var filter: Filter = getFilter()

    private var state: SearchStates = SearchStates.ServerError(false)
    private val stateLiveData = MutableLiveData<SearchStates>()
    fun getState(): LiveData<SearchStates> = stateLiveData

    private val vacancyList = mutableListOf<Vacancy>()
    private var searchJob: Job? = null
    private var page = 0
    private var maxPage = 0
    private var founded = 0
    private val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            filter.request = it
            search()
        }
    private val pageLoaderDebounce =
        debounce<Unit>(PAGE_LOAD_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            search()
        }

    fun loadJobs(text: String) {
        if (text.isBlank() || text == filter.request) return
        searchDebounce(text)
    }

    private fun search() {
        state = SearchStates.Loading
        stateLiveData.value = state
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


    private fun getFilter() = getSearchFilterUseCase.execute()

    fun refreshFilter() {
        val newFiler = getFilter()
        newFiler.request = filter.request
        newFiler.page = filter.page
        if (newFiler != filter) {
            newFiler.page = 0
            maxPage = 0
            when (state) {
                is SearchStates.Success -> {
                    (state as SearchStates.Success).filterStates = checkFilterState()
                    stateLiveData.value = state
                }

                is SearchStates.FilterIconStatus -> {
                    state =
                        if (checkFilterState())
                            SearchStates.FilterIconStatus(true)
                        else
                            SearchStates.FilterIconStatus(false)
                    stateLiveData.value = state
                }

                is SearchStates.ServerError -> {
                    (state as SearchStates.ServerError).filterStates =
                        checkFilterState()
                    stateLiveData.value = state
                }

                is SearchStates.InvalidRequest -> {
                    (state as SearchStates.InvalidRequest).filterStates =
                        checkFilterState()
                    stateLiveData.value = state
                }

                is SearchStates.ConnectionError -> {
                    (state as SearchStates.ConnectionError).filterStates =
                        checkFilterState()
                    stateLiveData.value = state
                }

                else -> Unit
            }
            filter = newFiler
            refreshSearch()
            return
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
                state = SearchStates.ServerError(checkFilterState())
                stateLiveData.value = state
            }

            Codes.SUCCESS -> {
                vacancyList.addAll(jobsInfo.jobs!!)
                page = jobsInfo.page
                maxPage = jobsInfo.pages
                if (page == 0)
                    founded = jobsInfo.found
                state =
                    jobsInfo.let {
                        SearchStates.Success(
                            jobList = vacancyList,
                            page = it.page,
                            found = founded,
                            filterStates = checkFilterState()
                        )
                    }
                stateLiveData.value = state
            }

            Codes.NO_NET_CONNECTION -> {
                state = SearchStates.ConnectionError(checkFilterState())
                stateLiveData.value = state
            }

            Codes.NO_RESULTS -> {
                state = SearchStates.InvalidRequest(
                    checkFilterState()
                )
                stateLiveData.value = state
            }
        }
    }

    private fun checkFilterState(): Boolean {
        if (filter.salary != null || filter.area != null || filter.industry != null || filter.onlyWithSalary) {
            return true
        }
        return false
    }

    fun clearAll() {
        state =
            if (checkFilterState())
                SearchStates.FilterIconStatus(true)
            else
                SearchStates.FilterIconStatus(false)
        stateLiveData.value = state
        vacancyList.clear()
        filter.request = ""
        searchJob?.cancel()
        maxPage = 0
        page = 0
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