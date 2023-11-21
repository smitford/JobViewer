package ru.practicum.android.diploma.filter.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.FilterInteractor
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.presentation.adapter.model.AreaDataInterface
import ru.practicum.android.diploma.filter.presentation.view_model.model.FilterParametersState
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.util.DataUtils
import ru.practicum.android.diploma.util.debounce

class ChoosingIndustryViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {
    private val _stateLiveData = MutableLiveData<FilterParametersState>()
    val stateLiveData: LiveData<FilterParametersState> get() = _stateLiveData

    private var industryForSave : Industry? = null
    private val _buttonIsVisibleLiveData = MutableLiveData<Boolean>()
    val buttonIsVisibleLiveData: LiveData<Boolean> get() = _buttonIsVisibleLiveData

    private var lastSearchText = ""

    private val searchDebounce =
        debounce<String>(DataUtils.SEARCH_DEBOUNCE_DELAY_MILS, viewModelScope, true) {

        }

    fun saveToVm(industryUi: AreaDataInterface) {
        industryForSave = UiConvertor.industryUiToIndustry(industryUi as AreaDataInterface.IndustryUi)
        _buttonIsVisibleLiveData.postValue(true)
    }

    fun saveIndustryToFilter(){
        industryForSave?.let { filterInteractor.saveIndustryToFilter(it) }
    }


    fun loadJobs(text: String) {
        if (text.isBlank()) return
        if (lastSearchText != text) {
            searchDebounce(text)
        }
        lastSearchText = text
    }

    fun getIndustries() {
        _stateLiveData.postValue(FilterParametersState.Loading)
        viewModelScope.launch {
            filterInteractor.getIndustries().collect { request ->
                requestHandler(request)
            }
        }
    }



    private fun requestHandler(request: DtoConsumer<List<Industry>>) {
        when (request) {
            is DtoConsumer.Error -> {
                when (request.errorCode) {
                    DataUtils.NO_RESULTS_CODE -> _stateLiveData.postValue(
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
                        UiConvertor.industriesToIndustriesUi(request.data)
                    )
                )
            }

            is DtoConsumer.NoInternet -> {
                _stateLiveData.postValue(FilterParametersState.Error(DataUtils.CONNECTION_ERROR))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        industryForSave = null
    }

}