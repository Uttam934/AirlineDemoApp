package com.example.airfiindia.data.repository

import com.example.airfiindia.roomdb.FavoriteAirline
import com.example.airfiindia.roomdb.FavoriteAirlineDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteReaderRepository @Inject constructor(
    private val dao: FavoriteAirlineDao
) {
    fun getAllFavorites(): Flow<List<FavoriteAirline>> = dao.getAllFavorites()
}
