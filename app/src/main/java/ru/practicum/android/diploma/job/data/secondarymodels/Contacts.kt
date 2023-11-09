package ru.practicum.android.diploma.job.data.secondarymodels

data class Contacts(
    val email: String?,
    val name: String?,
    val phones: List<Phones>?
) {
}