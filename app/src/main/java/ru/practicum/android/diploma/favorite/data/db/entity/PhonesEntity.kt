package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Phones")
data class PhonesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val idVacancy:String,
    val comment: String?,
    val formatted: String?
)
