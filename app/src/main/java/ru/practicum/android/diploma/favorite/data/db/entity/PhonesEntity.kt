package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phones")
data class PhonesEntity(
    @PrimaryKey
    val id:String,
    val comment: String?,
    val formatted: String?
)
