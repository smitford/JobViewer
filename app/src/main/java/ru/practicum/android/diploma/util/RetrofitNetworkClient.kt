package ru.practicum.android.diploma.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.search.data.models.JobSearchRequest
import ru.practicum.android.diploma.search.data.models.ResponseDto
import ru.practicum.android.diploma.search.data.models.ResultCodes

class RetrofitNetworkClient(val context: Context) : NetworkClient {

    override var lock = Any()

    private val retrofitHh =
        Retrofit.Builder()
            .baseUrl(BASE_HH_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val hhService = retrofitHh.create(HhApiJobInfo::class.java)

    override suspend fun doRequest(dto: Any): ResponseDto {

        if (!isConnected()) return ResponseDto()

        return when (dto) {
            is JobSearchRequest -> try {
                val resp = hhService.getJobList(
                    bearer = BuildConfig.HH_ACCESS_TOKEN,
                    request = dto
                )
                resp.apply { responseCode = ResultCodes.SUCCESS }
            } catch (e: Exception) {
                ResponseDto().apply { responseCode = ResultCodes.ERROR }
            }
            //Тут добавляете реализацию своего запроса

            else -> ResponseDto().apply { responseCode = ResultCodes.ERROR }
        }
    }

    @SuppressLint("NewApi")
    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }

    companion object {
        const val BASE_HH_API = "https://api.hh.ru/"
    }
}

