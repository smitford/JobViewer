package ru.practicum.android.diploma.filter.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.presentation.view_model.model.FilterParametersState
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.DataUtils

class ChoosingRegionViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {

    private val _stateLiveData = MutableLiveData<FilterParametersState>()
    val stateLiveData: LiveData<FilterParametersState> get() = _stateLiveData

    fun getRegionList() {
        _stateLiveData.postValue(FilterParametersState.Loading)
        viewModelScope.launch {
            filterInteractor.getRegions().collect { request ->
                requestHandler(request)
            }
        }
    }

    private fun requestHandler(request: DtoConsumer<List<Region>>) {
        when (request) {
            is DtoConsumer.Error -> {
                _stateLiveData.postValue(FilterParametersState.Error(DataUtils.ERROR))
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