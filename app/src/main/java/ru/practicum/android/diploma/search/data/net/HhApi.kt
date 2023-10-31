package ru.practicum.android.diploma.search.data.net

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.practicum.android.diploma.search.data.models.HhAuthRequest
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.Response

interface HhApi {
    @GET("vacancies")
    suspend fun getJobList(
        @Header("Authorization") token: String,
        @Body request: JobSearchRequest
    )

    /*
       @POST("oauth/token")
       suspend fun authenticate(@Body request: HhAuthRequest): Response

     */
}