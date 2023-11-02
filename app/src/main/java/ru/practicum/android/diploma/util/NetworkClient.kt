package ru.practicum.android.diploma.util

import ru.practicum.android.diploma.search.data.models.ResponseDto


interface NetworkClient {
    suspend fun doRequest(dto: Any): ResponseDto
    var lock: Any
}