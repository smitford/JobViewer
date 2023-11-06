package ru.practicum.android.diploma.favorite.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.practicum.android.diploma.favorite.data.db.entity.FavoriteEntity

@Dao
interface FavoriteDAO {

    // Получить треки
    @Query("SELECT * FROM Favorite")
    fun get(): List<FavoriteEntity>

    // Добавить в избранное
    @Insert(entity = FavoriteEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun add(albumEntity: FavoriteEntity)

    // Удалить из избранного
    @Query("DELETE FROM Favorite WHERE url LIKE :url")
    fun delete(url: String)

    // Узнать есть ли в избранном
    @Query("SELECT EXISTS (SELECT 1 FROM Favorite WHERE url LIKE :url)")
    fun included(url: String): Boolean

}