package com.example.airfiindia.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [FavoriteAirline::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteAirlineDao(): FavoriteAirlineDao
}