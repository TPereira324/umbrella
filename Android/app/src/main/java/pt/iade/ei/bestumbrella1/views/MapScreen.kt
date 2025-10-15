package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController) {
    val lisboaCenter = LatLng(38.7682, -9.0985)

    // Pontos das estações Best Umbrella
    val stations = listOf(
        LatLng(38.7685, -9.0975), // Moscavide centro
        LatLng(38.7692, -9.0943), // Moscavide norte
        LatLng(38.7670, -9.1008), // Moscavide sul
        LatLng(38.7635, -9.0938), // IADE - Parque das Nações
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisboaCenter, 15f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mapa de Estações") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("qrscanner") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.QrCodeScanner, contentDescription = "Desbloquear com QR")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                // Adicionar marcadores das estações
                stations.forEachIndexed { index, location ->
                    Marker(
                        state = MarkerState(position = location),
                        title = "Estação ${index + 1}",
                        snippet = "Guarda-chuvas disponíveis: ${(2..6).random()}"
                    )
                }
            }

            // Texto informativo
            Text(
                text = "Clique no botão 📷 para desbloquear um guarda-chuva!",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 70.dp)
                    .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.8f))
                    .padding(8.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
