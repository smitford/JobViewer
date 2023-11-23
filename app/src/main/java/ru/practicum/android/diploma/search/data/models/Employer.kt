package ru.practicum.android.diploma.search.data.models

import com.google.gson.annotations.SerializedName

data class Employer(
    val id: String?,
    @SerializedName("logo_urls")
    val logoUrls: LogoUrls?,
    val name: String,
    val url: String?
)