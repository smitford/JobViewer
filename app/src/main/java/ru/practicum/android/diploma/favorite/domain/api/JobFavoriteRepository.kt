package ru.practicum.android.diploma.favorite.domain.api

interface JobFavoriteRepository {

    suspend fun add(url:String)
    suspend fun delete(url:String)
    suspend fun included(url:String): Boolean

}