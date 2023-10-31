package ru.practicum.android.diploma.search.data.net

import android.content.Context
import ru.practicum.android.diploma.search.data.models.Response

class RetrofitNetworkClient(val context: Context) : NetworkClient {

    override var lock = Any()

    override suspend fun doRequest(dto: Any): Response {
        TODO("Not yet implemented")
    }
}