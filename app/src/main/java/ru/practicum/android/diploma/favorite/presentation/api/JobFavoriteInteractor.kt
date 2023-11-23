package ru.practicum.android.diploma.favorite.presentation.api

interface JobFavoriteInteractor {
    suspend fun add(url:String)
    suspend fun delete(url:String)
    suspend fun included(url:String) : Boolean
}