package ru.practicum.android.diploma.favorite.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "KeySkills")
data class KeySkillsEntity(
    @PrimaryKey
    val id:String,
    val name: String
)
