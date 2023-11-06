package ru.practicum.android.diploma.job.presentation.viewmodel

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.job.sharing.domain.interactor.SharingInteractor

class JobFragmentViewModel(private val sharingInteractor: SharingInteractor) : ViewModel() {

    fun shareJobLink(jobLink: String) {
        sharingInteractor.shareJobLink(jobLink)
    }

    fun shareEmail(email: String) {
        sharingInteractor.shareEmail(email)
    }

    fun sharePhoneNumber(phoneNumber: String) {
        sharingInteractor.sharePhoneNumber(phoneNumber)
    }
}