package ru.practicum.android.diploma.search.data.net

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import ru.practicum.android.diploma.search.data.models.JobSearchRequest

interface HhApi {
    @GET("vacancies")
    suspend fun getJobList(
        @Header("Authorization") token: String,
        @Body request: JobSearchRequest
    )


}