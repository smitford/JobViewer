package ru.practicum.android.diploma.sharing.domain.repository

interface SharingRepository {
    fun shareJobLink(jobLink: String)
    fun shareEmail(email: String)
    fun sharePhoneNumber(phoneNumber: String)
}