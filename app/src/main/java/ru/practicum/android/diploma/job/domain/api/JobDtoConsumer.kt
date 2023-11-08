package ru.practicum.android.diploma.job.domain.api

sealed interface JobDtoConsumer<T> {
    data class Error<T>(val errorCode: Int) : JobDtoConsumer<T>
    data class Success<T>(val data: T) : JobDtoConsumer<T>
    data class NoInternet<T>(val errorCode: Int) : JobDtoConsumer<T>
}
