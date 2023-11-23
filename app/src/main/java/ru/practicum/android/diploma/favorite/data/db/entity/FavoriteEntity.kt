package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class FavoriteEntity(
    @PrimaryKey
    val id:String,
    val name:String?,
    val salaryFrom: String?,
    val employerLogoUrl: String?,
    val employerUrl: String?,
    val employerName: String?,
    val area: String?,
    val address: String?,
    val experience: String?,
    val employment: String?,
    val description: String?,
    val contactsName: String?,
    val email: String?,
)