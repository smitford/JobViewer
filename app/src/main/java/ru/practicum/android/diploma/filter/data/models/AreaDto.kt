package ru.practicum.android.diploma.filter.data.models

import com.google.gson.annotations.SerializedName

data class AreaDto(
    val name: String,
    val id: String,
    @SerializedName("parent_id") val parentId: String?,
    val areas: List<AreaDto>
)
