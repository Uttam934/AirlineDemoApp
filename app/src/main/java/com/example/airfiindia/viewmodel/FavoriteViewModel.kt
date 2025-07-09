package com.example.airfiindia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airfiindia.data.repository.FavoriteRepository
import com.example.airfiindia.roomdb.FavoriteAirline
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository,
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteAirline>> = repository
        .getAllFavorites()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addFavorite(fav: FavoriteAirline) {
        viewModelScope.launch { repository.addToFavorites(fav) }
    }

    fun removeFavorite(id: String) {
       // viewModelScope.launch { repository.deleteById(id) }
    }
}
