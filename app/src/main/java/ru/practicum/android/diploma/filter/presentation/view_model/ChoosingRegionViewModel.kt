package ru.practicum.android.diploma.filter.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface
import ru.practicum.android.diploma.filter.presentation.view_model.model.FilterParametersState
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.DataUtils
import ru.practicum.android.diploma.util.DataUtils.Companion.NO_RESULTS_CODE
import ru.practicum.android.diploma.util.DataUtils.Companion.SEARCH_DEBOUNCE_DELAY_MILS
import ru.practicum.android.diploma.util.debounce

class ChoosingRegionViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {

    private val _stateLiveData = MutableLiveData<FilterParametersState>()
    val stateLiveData: LiveData<FilterParametersState> get() = _stateLiveData

    private var lastSearchText = ""

    private val searchDebounce =
        debounce<String>(SEARCH_DEBOUNCE_DELAY_MILS, viewModelScope, true) {
            searchRegion(it)
        }

    fun loadJobs(text: String) {
        if (text.isBlank()) return
        if (lastSearchText != text) {
            searchDebounce(text)
        }
        lastSearchText = text
    }

    fun getRegionList() {
        _stateLiveData.postValue(FilterParametersState.Loading)
        viewModelScope.launch {
            filterInteractor.getRegions().collect { request ->
                requestHandler(request)
            }
        }
    }

    fun searchRegion(name: String) {
        _stateLiveData.postValue(FilterParametersState.Loading)
        viewModelScope.launch {
            filterInteractor.getAreasByName(name).collect { request ->
                requestHandler(request)
            }
        }
    }

    fun saveRegionInFilter(region: AreaDataInterface) {
        filterInteractor.saveRegionToFilter(UiConvertor.regionUiToRegion(region))
    }

    private fun requestHandler(request: DtoConsumer<List<Region>>) {
        when (request) {
            is DtoConsumer.Error -> {
                when (request.errorCode) {
                    NO_RESULTS_CODE -> _stateLiveData.postValue(
                        FilterParametersState.Error(
                            DataUtils.NOT_FOUND
                        )
                    )

                    else -> _stateLiveData.postValue(FilterParametersState.Error(DataUtils.ERROR))
                }

            }

            is DtoConsumer.Success -> {
                _stateLiveData.postValue(
                    FilterParametersState.ParametersResult(
                        UiConvertor.regionListToRegionUiList(request.data)
                    )
                )
            }

            is DtoConsumer.NoInternet -> {
                _stateLiveData.postValue(FilterParametersState.Error(DataUtils.CONNECTION_ERROR))
            }
        }
    }
}