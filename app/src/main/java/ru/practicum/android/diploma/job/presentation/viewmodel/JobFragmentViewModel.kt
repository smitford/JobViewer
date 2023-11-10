package ru.practicum.android.diploma.job.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.job.domain.models.JobForScreenInfo
import ru.practicum.android.diploma.job.domain.interactor.LoadJobInteractor
import ru.practicum.android.diploma.job.presentation.states.JobStates
import ru.practicum.android.diploma.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.search.domain.models.Codes
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class JobFragmentViewModel(
    private val sharingInteractor: SharingInteractor,
    private val loadJobInteractor: LoadJobInteractor
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
            loadJobInteractor.getJob(id = id).collect { jobForScreenInfo ->
                requestHandler(jobForScreenInfo)
            }
        }
    }

    private fun requestHandler(jobForScreenInfo: JobForScreenInfo) =
        when (jobForScreenInfo.responseCodes) {
            Codes.ERROR -> {
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }

            Codes.SUCCESS -> {
                jobForScreenInfo.job?.let { JobStates.Success(it) } ?: SearchStates.InvalidRequest
                Log.d("requestHandlerJob", jobForScreenInfo.job?.employerName ?: "default")
            }

            Codes.NO_NET_CONNECTION -> {
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }
            Codes.NO_RESULTS->{
                Log.d("requestHandlerJob", jobForScreenInfo.responseCodes.name)
            }
        }
}