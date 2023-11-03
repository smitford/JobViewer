package ru.practicum.android.diploma.util

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.JobSearchResponseDto

interface HhApiJobInfo {
    @Headers(
        "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}",
        "HH-User-Agent: Diplom_Yandex_HH (alk68@yandex.ru)"
    )
    @GET("vacancies")
    suspend fun getJobList(
        @QueryMap options: HashMap<String, String>
    ): JobSearchResponseDto

    // Ниже добавляйте свои ф-ии
}