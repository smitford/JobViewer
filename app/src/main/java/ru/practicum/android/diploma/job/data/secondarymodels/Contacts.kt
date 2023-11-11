package ru.practicum.android.diploma.job.data.secondarymodels

data class Contacts(
    val call_tracking_enabled: Boolean?,
    val email: String?,
    val name: String?,
    val phones: List<Phones>?
) {
}