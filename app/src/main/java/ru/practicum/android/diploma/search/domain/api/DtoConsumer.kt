package ru.practicum.android.diploma.search.domain.api

sealed interface DtoConsumer<T> {
    data class Error<T>(val errorCode: Int) : DtoConsumer<T>
    data class Success<T>(val data: T) : DtoConsumer<T>
    data class NoInternet<T>(val errorCode: Int) : DtoConsumer<T>
}
