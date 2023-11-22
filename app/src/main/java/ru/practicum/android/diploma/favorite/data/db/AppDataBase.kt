package ru.practicum.android.diploma.favorite.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.favorite.data.db.dao.FavoriteDAO
import ru.practicum.android.diploma.favorite.data.db.dao.KeySkillsDAO
import ru.practicum.android.diploma.favorite.data.db.dao.PhonesDAO
import ru.practicum.android.diploma.favorite.data.db.entity.FavoriteEntity
import ru.practicum.android.diploma.favorite.data.db.entity.KeySkillsEntity
import ru.practicum.android.diploma.favorite.data.db.entity.PhonesEntity

@Database(
    version = 2,
    entities = [FavoriteEntity::class, KeySkillsEntity::class, PhonesEntity::class],
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO
    abstract fun keySkillsDAO(): KeySkillsDAO
    abstract fun phonesDAO(): PhonesDAO
}