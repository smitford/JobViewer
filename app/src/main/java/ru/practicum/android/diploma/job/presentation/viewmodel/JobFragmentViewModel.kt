package ru.practicum.android.diploma.job.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.favorite.presentation.api.JobFavoriteInteractor
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.job.domain.interactor.LoadJobInteractor
import ru.practicum.android.diploma.job.domain.models.JobForScreen
import ru.practicum.android.diploma.job.presentation.states.JobScreenState
import ru.practicum.android.diploma.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class JobFragmentViewModel(
    private val sharingInteractor: SharingInteractor,
    private val loadJobInteractor: LoadJobInteractor,
    private val jobFavoriteInteractor: JobFavoriteInteractor
) : ViewModel() {

    private val _favorite = MutableLiveData<Boolean>()
    fun observeFavoriteLifeData(): LiveData<Boolean> = _favorite

    private val _state = MutableLiveData<JobScreenState>()
    fun observeJobScreenLiveData(): LiveData<JobScreenState> = _state

    fun shareJobLink(jobLink: String) {
        sharingInteractor.shareJobLink(jobLink)
    }

    fun shareEmail(email: String) {
        sharingInteractor.shareEmail(email)
    }

    fun sharePhoneNumber(phoneNumber: String) {
        sharingInteractor.sharePhoneNumber(phoneNumber)
    }

    fun getJob(id: String) {
        _state.postValue(JobScreenState.Loading)
        viewModelScope.launch {
            loadJobInteractor.getJob(id = id).collect { jobForScreenInfo ->
                requestHandler(jobForScreenInfo)
            }
        }
    }

    private fun requestHandler(jobForScreenInfo: JobForScreenInfo) =
        when (jobForScreenInfo.responseCodes) {
            Codes.ERROR -> {
                _state.postValue(JobScreenState.ServerError)
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                _state.postValue(jobForScreenInfo.job?.let { JobScreenState.Success(it) }
                    ?: JobScreenState.InvalidRequest)
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }

            Codes.NO_NET_CONNECTION -> {
                _state.postValue(JobScreenState.ConnectionError)
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }
            Codes.NO_RESULTS->{
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }
        }

    fun addToFavorite(job:JobForScreen){
        viewModelScope.launch {
            jobFavoriteInteractor.add(job)
            _favorite.value = true
        }
    }

    fun includedToFavorite(id: String){
        viewModelScope.launch {
            _favorite.value = jobFavoriteInteractor.included(id)
        }
    }

    fun deleteFromFavorite(id:String){
        viewModelScope.launch {
            jobFavoriteInteractor.delete(id)
            _favorite.value = false
        }


    }

}