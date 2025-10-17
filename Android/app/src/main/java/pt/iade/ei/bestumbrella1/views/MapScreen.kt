package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.*

data class Station(
    val name: String,
    val location: LatLng,
    val available: Int,
    val total: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreenWithMarkers(navController: NavController) {
    val lisboaCenter = LatLng(38.7682, -9.0985)

    val stations = listOf(
        Station("Metro Moscavide", LatLng(38.7685, -9.0975), 7, 10),
        Station("Metro Oriente", LatLng(38.7692, -9.0943), 5, 8),
        Station("Parque das Nações", LatLng(38.7670, -9.1008), 6, 10),
        Station("IADE", LatLng(38.7635, -9.0938), 3, 6)
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisboaCenter, 15f)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Best Umbrella") })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(
                        selected = true,
                        onClick = {},
                        label = { Text("Todas") }
                    )
                    FilterChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Disponíveis") }
                    )

                    FilterChip(
                        selected = false,
                        onClick = {},
                        label = { Text("Próximas") }
                    )
                }
                Text(
                    text = "🟢 Localização ativa",
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("qrscanner") },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.QrCodeScanner, contentDescription = "Desbloquear com QR")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Map, contentDescription = "Mapa") },
                    label = { Text("Mapa") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "Scanner") },
                    label = { Text("Scanner") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.History, contentDescription = "Histórico") },
                    label = { Text("Histórico") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                stations.forEach { station ->
                    Marker(
                        state = MarkerState(position = station.location),
                        title = station.name,
                        snippet = "${station.available}/${station.total} disponíveis",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                stations.forEach {
                    Text(
                        text = "${it.name} — ${it.available}/${it.total} disponíveis",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Text(
                text = "Clique no botão para desbloquear um guarda-chuva!",
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


