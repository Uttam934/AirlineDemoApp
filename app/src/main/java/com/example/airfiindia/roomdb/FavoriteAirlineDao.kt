package com.example.airfiindia.roomdb


import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteAirlineDao {

    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteAirline>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(airline: FavoriteAirline)

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun deleteById(id: String)
}
