package ru.practicum.android.diploma.search.data.models

import com.google.gson.annotations.SerializedName

class HhAuthResponse (@SerializedName("access_token") val token: String) :Response()