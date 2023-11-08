package ru.practicum.android.diploma.job.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.job.domain.usecases.impl.LoadJobUseCase
import ru.practicum.android.diploma.job.domain.usecases.impl.LoadJobUseCaseImpl
import ru.practicum.android.diploma.job.presentation.JobStates
import ru.practicum.android.diploma.job.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.domain.models.JobsInfo
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class JobFragmentViewModel(
    private val sharingInteractor: SharingInteractor,
    private val loadJobUseCaseImpl: LoadJobUseCase
) : ViewModel() {

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
        viewModelScope.launch {
            loadJobUseCaseImpl.getJob(id = id).collect { jobForScreenInfo ->
                requestHandler(jobForScreenInfo)
            }
        }
    }

    private fun requestHandler(jobForScreenInfo: JobForScreenInfo) =
        when (jobForScreenInfo.responseCodes) {
            Codes.ERROR -> {
                //stateLiveData.value = SearchStates.ServerError
                Log.d("getJobState", jobForScreenInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                //stateLiveData.value =
                jobForScreenInfo.job?.let { JobStates.Success(it) } ?: SearchStates.InvalidRequest
                Log.d("getJobState", jobForScreenInfo.responseCodes.name)
            }

            Codes.NO_NET_CONNECTION -> {
                //stateLiveData.value = SearchStates.ConnectionError
                Log.d("getJobState", jobForScreenInfo.responseCodes.name)
            }
        }
}