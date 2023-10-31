package ru.practicum.android.diploma.search.domain.consumer

sealed interface DtoConsumer<T> {
    data class Error<T>(val errorCode: Int) : DtoConsumer<T>
    data class Success<T>(val data: T) : DtoConsumer<T>
}
