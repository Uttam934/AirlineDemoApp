package com.example.airfiindia.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airfiindia.data.model.Airline
import com.example.airfiindia.data.repository.AirlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirlineViewModel @Inject constructor(
    private val repository: AirlineRepository
) : ViewModel() {

    private val _airlines = MutableStateFlow<List<Airline>>(emptyList())
    val airlines: StateFlow<List<Airline>> = _airlines.asStateFlow()

    init {
        viewModelScope.launch {
            repository.loadAirlines().collect {
                _airlines.value = it
            }
        }
    }
}
