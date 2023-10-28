package ru.practicum.android.diploma.favorite.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.practicum.android.diploma.favorite.data.db.dao.FavoriteDAO
import ru.practicum.android.diploma.favorite.data.db.entity.FavoriteEntity

@Database(version = 1, entities = [FavoriteEntity::class], exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun favoriteDAO(): FavoriteDAO
}