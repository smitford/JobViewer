package ru.practicum.android.diploma.sharing.domain.interactor

interface SharingInteractor {
    fun shareJobLink(jobLink: String)
    fun shareEmail(email: String)
    fun sharePhoneNumber(phoneNumber: String)
}