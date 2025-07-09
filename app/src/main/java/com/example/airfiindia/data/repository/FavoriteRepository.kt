package com.example.airfiindia.data.repository

import com.example.airfiindia.roomdb.FavoriteAirline
import com.example.airfiindia.roomdb.FavoriteAirlineDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dao: FavoriteAirlineDao) {
    suspend fun addToFavorites(airline: FavoriteAirline) = dao.insert(airline)
      fun getAllFavorites(): Flow<List<FavoriteAirline>> = dao.getAllFavorites()
}
