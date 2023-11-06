package ru.practicum.android.diploma.job.sharing.domain.interactor

interface SharingInteractor {
    fun shareJobLink(jobLink: String)
    fun shareEmail(email: String)
    fun sharePhoneNumber(phoneNumber: String)
}