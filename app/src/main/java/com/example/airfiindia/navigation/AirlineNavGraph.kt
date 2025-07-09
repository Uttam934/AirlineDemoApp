package com.example.airfiindia.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.airfiindia.screens.AirlineDetailScreen
import com.example.airfiindia.screens.AirlineListScreen
import com.example.airfiindia.screens.FavoritesScreen
import com.example.airfiindia.viewmodel.AirlineViewModel

@Composable
fun AirlineNavGraph(
    navController: NavHostController,
    viewModel: AirlineViewModel
) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            AirlineListScreen(navController = navController, viewModel = viewModel)
        }
        composable("details/{airlineId}") { backStackEntry ->
            val viewModel: AirlineViewModel = hiltViewModel()
            val id = backStackEntry.arguments?.getString("airlineId")
            val airline = viewModel.airlines.collectAsState().value.find { it.id == id }
            airline?.let {
                AirlineDetailScreen(airline = it,navController = navController)
            }
        }

        composable("favorites") {
            FavoritesScreen(navController = navController)
        }




    }
}
