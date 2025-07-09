package com.example.airfiindia.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.airfiindia.R
import com.example.airfiindia.viewmodel.FavoriteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController, // ⬅️ Required to go back
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val favorites by favoriteViewModel.favorites.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favorite Airlines", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2)),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(favorites) { airline ->
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
                    }
                )
                Divider()
            }
        }
    }
}
