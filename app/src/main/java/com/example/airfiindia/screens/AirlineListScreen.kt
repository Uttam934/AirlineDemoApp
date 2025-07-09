package com.example.airfiindia.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.airfiindia.R
import com.example.airfiindia.roomdb.FavoriteAirline
import com.example.airfiindia.viewmodel.AirlineViewModel
import com.example.airfiindia.viewmodel.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirlineListScreen(
    navController: NavController,
    viewModel: AirlineViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val airlines by viewModel.airlines.collectAsState()
    val favorites by favoriteViewModel.favorites.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    val filteredAirlines = airlines.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.country.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Airlines List", color = Color.White) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2)),
                    actions = {
                        IconButton(onClick = {
                            navController.navigate("favorites")
                        }) {
                            Icon(
                                Icons.Default.Favorite ,
                                contentDescription = "View Favorites",
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                )

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Search by name or country") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    singleLine = true
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(filteredAirlines) { airline ->
                val isFavorite = favorites.any { it.id == airline.id }

                ListItem(
                    headlineContent = { Text(airline.name) },
                    supportingContent = { Text(airline.country) },
                    leadingContent = {
                        AsyncImage(
                            model = airline.logo_url,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            placeholder = painterResource(id = R.drawable.error),
                            error = painterResource(id = R.drawable.error)
                        )
                    },
                    trailingContent = {
                        IconButton(onClick = {
                            if (!isFavorite) {
                                val favorite = FavoriteAirline(
                                    id = airline.id,
                                    name = airline.name,
                                    country = airline.country,
                                    headquarters = airline.headquarters,
                                    fleet_size = airline.fleet_size,
                                    website = airline.website,
                                    logo_url = airline.logo_url
                                )
                                favoriteViewModel.addFavorite(favorite)

                                Toast.makeText(
                                    context,
                                    "${airline.name} added to favorites",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favorite Status",
                                tint = if (isFavorite) Color.Red else Color.Gray,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("details/${airline.id}")
                        }
                )
                Divider()
            }
        }
    }
}
