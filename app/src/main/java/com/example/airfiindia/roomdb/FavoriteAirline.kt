package com.example.airfiindia.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteAirline(
    @PrimaryKey val id: String,
    val name: String,
    val country: String,
    val headquarters: String,
    val fleet_size: Int,
    val website: String,
    val logo_url: String
)
