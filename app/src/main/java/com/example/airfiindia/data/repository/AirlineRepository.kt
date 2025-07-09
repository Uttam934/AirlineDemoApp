package com.example.airfiindia.data.repository

import android.content.Context
import com.example.airfiindia.data.model.Airline
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AirlineRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun loadAirlines(): Flow<List<Airline>> = flow {
        val jsonString = context.assets.open("airlines.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<Airline>>() {}.type
        val airlines: List<Airline> = Gson().fromJson(jsonString, type)
        emit(airlines)
    }
}
