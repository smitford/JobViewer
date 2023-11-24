package ru.practicum.android.diploma.filter.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.filter.domain.FilterInteractor

class PlaceOfWorkViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {

    private val _country = MutableLiveData<String>()
    val country: LiveData<String> get() = _country

    private val _region = MutableLiveData<String>()
    val region: LiveData<String> get() = _region

    fun updateInfoFragment() {
        val filterParameters = filterInteractor.getFilterSettings()
        _country.value = filterParameters.country ?: ""
        _region.value = filterParameters.area ?: ""
    }

    fun clearCountry() {
        filterInteractor.clearCountryInFilter()
        updateInfoFragment()
    }

    fun clearRegion() {
        filterInteractor.deleteRegionFromFilter()
        updateInfoFragment()
    }
}