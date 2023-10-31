package ru.practicum.android.diploma.search.data.models

data class HhAuthRequest(
    val clientId: String,
    val clientSecret: String,
    val grantType: String = "client_credentials"
)