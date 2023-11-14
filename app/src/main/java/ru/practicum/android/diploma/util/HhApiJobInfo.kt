package ru.practicum.android.diploma.util

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.filter.data.models.CountriesResponse
import ru.practicum.android.diploma.filter.data.models.CountryDto
import ru.practicum.android.diploma.job.data.mainmodels.JobDtoForScreenResponse
import ru.practicum.android.diploma.search.data.models.JobSearchResponseDto
import ru.practicum.android.diploma.similarjob.data.dto.JobSearchSimilarResponseDto

interface HhApiJobInfo {
    //Поиск вакансий
    @Headers(
        HEADER_AUTH,
        USER
    )
    @GET("vacancies")
    suspend fun getJobList(
        @QueryMap options: HashMap<String, String>
    ): JobSearchResponseDto

    //Просмотр вакансии
    @Headers(
        HEADER_AUTH,
        USER
    )
    @GET("vacancies/{vacancy_id}")
    suspend fun getJobById(@Path("vacancy_id") id: String): JobDtoForScreenResponse

    //Получение списка стран
    @Headers(
        USER
    )
    @GET("areas/countries")
    suspend fun getCountries(): List<CountryDto>

    @Headers(
        HEADER_AUTH,
        USER
    )
    @GET("vacancies/{vacancy_id}/similar_vacancies")
    suspend fun getSimilarVacancies(@Path("vacancy_id")vacancyId: String) :
            JobSearchSimilarResponseDto

    companion object{
        const val HEADER_AUTH = "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
        const val USER = "HH-User-Agent: Diplom_Yandex_HH (alk68@yandex.ru)"
    }
}