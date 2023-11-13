package ru.practicum.android.diploma.sharing.domain.impl

import ru.practicum.android.diploma.sharing.domain.interactor.SharingInteractor
import ru.practicum.android.diploma.sharing.domain.repository.SharingRepository

class SharingInteractorImpl(private val sharingRepository: SharingRepository) : SharingInteractor {
    override fun shareJobLink(jobLink: String) {
        sharingRepository.shareJobLink(jobLink)
    }

    override fun shareEmail(email: String) {
        sharingRepository.shareEmail(email)
    }

    override fun sharePhoneNumber(phoneNumber: String) {
        sharingRepository.sharePhoneNumber(phoneNumber)
    }
}