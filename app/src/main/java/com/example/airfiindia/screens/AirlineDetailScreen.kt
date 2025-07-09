package com.example.airfiindia.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.airfiindia.data.model.Airline

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirlineDetailScreen(airline: Airline,navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Airline Details", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2) // Blue
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Name: ${airline.name}", style = MaterialTheme.typography.headlineSmall)
            Text("Country: ${airline.country}")
            Text("Headquarters: ${airline.headquarters}")
            Text("Fleet Size: ${airline.fleet_size}")
            Spacer(modifier = Modifier.height(8.dp))
            ClickableText(
                text = AnnotatedString(airline.website),
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Blue),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(airline.website))
                    context.startActivity(intent)
                }
            )
        }
    }
}
