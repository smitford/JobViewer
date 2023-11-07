package ru.practicum.android.diploma.search.data.models

import com.google.gson.annotations.SerializedName

data class LogoUrls(
    @SerializedName("240")
    val mediumIcon: String?,
    @SerializedName("90")
    val smallIcon: String?,
    val original: String?
)