package ru.practicum.android.diploma.util

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.JobSearchResponseDto

interface HhApiJobInfo {
    @GET("vacancies")
    suspend fun getJobList(
        @Header("Authorization") bearer: String,
        @Body request: JobSearchRequest
    ) : JobSearchResponseDto

    // Ниже добавляйте свои ф-ии
}