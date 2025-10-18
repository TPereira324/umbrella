package pt.iade.ei.bestumbrella1.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
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
        Station("Metro Moscavide", LatLng(38.7687, -9.0974), 8, 10),
        Station("Metro Oriente", LatLng(38.7689, -9.0942), 4, 8),
        Station("Parque das Nações Norte", LatLng(38.7715, -9.0980), 6, 10),
        Station("IADE", LatLng(38.7633, -9.0941), 3, 6),
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lisboaCenter, 14.8f)
    }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(title = { Text("Best Umbrella ☂️") })
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FilterChip(selected = true, onClick = {}, label = { Text("Todas") })
                    FilterChip(selected = false, onClick = {}, label = { Text("Disponíveis") })
                    FilterChip(selected = false, onClick = {}, label = { Text("Próximas") })
                }
                Text(
                    text = "🟢 Localização ativa",
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
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
                    onClick = { navController.navigate("qrscanner") },
                    icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "Scanner") },
                    label = { Text("Scanner") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.History, contentDescription = "Histórico") },
                    label = { Text("Histórico") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF2196F3).copy(alpha = 0.7f),
                            Color(0xFFE3F2FD)
                        )
                    )
                )
        ) {
            // 🗺️ Google Map com marcadores
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                stations.forEach { station ->
                    Marker(
                        state = MarkerState(position = station.location),
                        title = station.name,
                        snippet = "${station.available}/${station.total} disponíveis ☂️"
                    )
                }
            }


            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Caixa das estações (à esquerda)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0x66000000))
                        .padding(12.dp)
                ) {
                    stations.take(3).forEach {
                        Text(
                            text = "☂️ ${it.name} — ${it.available}/${it.total} disp.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                // Botão (à direita)
                ExtendedFloatingActionButton(
                    onClick = { navController.navigate("qrscanner") },
                    containerColor = Color(0xFF1976D2),
                    contentColor = Color.White,
                    icon = { Icon(Icons.Default.QrCodeScanner, contentDescription = "Desbloquear") },
                    text = { Text("Desbloquear") },
                    modifier = Modifier
                        .height(56.dp)
                        .shadow(8.dp, shape = MaterialTheme.shapes.medium)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMapScreenWithMarkers() {
    val navController = rememberNavController()
    MapScreenWithMarkers(navController)
}
