package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phones")
data class PhonesEntity(
    @PrimaryKey(true)
    val id:Int = 0,
    val idVacancy:String?,
    val comment: String?,
    val formatted: String?
)
