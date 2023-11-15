package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KeySkills")
data class KeySkillsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val idVacancy:String,
    val name: String
)
