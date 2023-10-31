package ru.practicum.android.diploma.search.data.net

import ru.practicum.android.diploma.search.data.models.Response


interface NetworkClient {

    suspend fun doRequest(dto: Any): Response
    var lock: Any
}